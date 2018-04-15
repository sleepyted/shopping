package servlet;

import bean.CartItem;
import bean.Order;
import bean.Result;
import bean.User;
import common.Util;
import service.GoodService;

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
			List<CartItem> cartItems;
			cartItems = (List<CartItem>) req.getSession().getAttribute("cart");
			if(null == cartItems){
				cartItems = new ArrayList<>();
				req.getSession().setAttribute("cart", cartItems);
			}
			switch (page){
				case "view":
					req.getRequestDispatcher("/page/user/cart.jsp").forward(req, resp);
					break;
				case "add":
					int goodId = Integer.valueOf(req.getParameter("goodId"));
					int num = Integer.valueOf(req.getParameter("num"));
					Boolean flag = false;
					for(int i = 0; i< cartItems.size(); i++) {
						if(cartItems.get(i).getGood().getId() == goodId){
							cartItems.get(i).setNum(cartItems.get(i).getNum()+num);
							flag = true;
						}
					}
					if(!flag){
						GoodService service = new GoodService();
						CartItem item = new CartItem(service.findGood(goodId), num);
						cartItems.add(item);
					}

					Util.writeJson(resp, new Result(1,"添加成功"));
					break;
				case "del":
					int goodId1 = Integer.valueOf(req.getParameter("goodId"));
					for(int i = 0; i< cartItems.size(); i++) {
						if(cartItems.get(i).getGood().getId() == goodId1){
							cartItems.remove(i);
						}
					}
					Util.writeJson(resp, new Result(1,"删除成功"));
					break;
				case "all":
					if(cartItems.size()>0) {
						Result result = new Result();
						result.setStatus(1);
						result.setList(cartItems);
						Util.writeJson(resp, result);
					}else {
						Util.writeJson(resp, new Result(0,"无商品"));
					}
					break;
				case "clear":
					cartItems.clear();
					Util.writeJson(resp, new Result(1,"购物车已清空"));
					break;
				case "buy":
					req.getRequestDispatcher("/page/good/buy.jsp").forward(req, resp);
					break;
			}
		}else {
			Util.writeJson(resp, new Result(0, "请先登陆"));
		}
	}
}
