package servlet;

import bean.CartItem;
import bean.Result;
import bean.User;
import common.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user");
		if(null != user){
			String page = req.getParameter("page");
			List<CartItem> cartItems = (List<CartItem>) req.getSession().getAttribute("cart");
			if(null == cartItems){
				List<CartItem> cart = new ArrayList<>();
				req.getSession().setAttribute("cart", cart);
			}
			switch (page){
				case "view":
					req.getRequestDispatcher("/page/user/cart.jsp").forward(req, resp);
					break;
				case "add":
					int goodId = Integer.valueOf(req.getParameter("goodId"));
					int num = Integer.valueOf(req.getParameter("num"));
					CartItem item = new CartItem(goodId, num);

					cartItems.add(item);
					Util.writeJson(resp, new Result(1,"添加成功"));
					break;
				case "del":
					int goodId1 = Integer.valueOf(req.getParameter("goodId"));
					for(CartItem c : cartItems) {
						if(c.getGoodId() == goodId1){
							cartItems.remove(c);
						}
					}
					Util.writeJson(resp, new Result(1,"删除成功"));
					break;
				case "all":
					Result result = new Result();
					result.setList(cartItems);
					Util.writeJson(resp, result);
					break;
				case "clear":
					cartItems.clear();
					Util.writeJson(resp, new Result(1,"购物车已清空"));
					break;
			}
		}else {
			Util.writeJson(resp, new Result(0, "请先登陆"));
		}
	}
}
