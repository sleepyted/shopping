package servlet;

import bean.CartItem;
import bean.Order;
import bean.User;
import service.OrderService;

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
@WebServlet("/order")
public class OrderServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");

		switch (page){
			case "user":
				User user = (User) req.getSession().getAttribute("user");
				OrderService service = new OrderService();
				List<Order> orders = service.finalUserOrders(user.getId());
				req.setAttribute("orders", orders);
				req.getRequestDispatcher("/page/user/orders.jsp").forward(req, resp);
				break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<CartItem> cartItems = (List<CartItem>) req.getSession().getAttribute("cart");
		User user = (User) req.getSession().getAttribute("user");
		if(null!=user && null!= cartItems && cartItems.size()>0) {
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");
			String addr = req.getParameter("addr");
			List<Order> orders = new ArrayList<>();
			OrderService service = new OrderService();
			for (int i = 0; i < cartItems.size(); i++) {
				Order order = new Order();
				order.setName(name);
				order.setUserId(user.getId());
				order.setTel(tel);
				order.setAddr(addr);
				order.setStatus(OrderService.STATUS_PAIED );
				order.setGoodId(cartItems.get(i).getGood().getId());
				order.setNum(cartItems.get(i).getNum());
				service.saveOrder(order);
			}
			cartItems.clear();
		}

		resp.sendRedirect("order?page=user");
	}
}
