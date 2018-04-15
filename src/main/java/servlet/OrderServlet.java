package servlet;

import bean.*;
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
		OrderService service = new OrderService();

		switch (page){
			case "user":
				User user = (User) req.getSession().getAttribute("user");
				List<Order> orders = service.finalUserOrders(user.getId());
				List<OrderItem> orderItems = service.getOrderItem(orders);
				req.setAttribute("orders", orderItems);
				req.getRequestDispatcher("/page/user/orders.jsp").forward(req, resp);
				break;
			case "confirm":
				String orderId = req.getParameter("orderId");
				Order order = new OrderService().findById(Integer.valueOf(orderId));
				service.updateOrderStatus(OrderService.STATUS_FINISH, Integer.valueOf(orderId));
				resp.sendRedirect("order?page=comment&goodId="+ order.getGoodId());
				break;
			case "comment":
				int goodId = Integer.valueOf(req.getParameter("goodId"));
				Comment comment = new Comment();

				comment.setGoodId(goodId);
				req.setAttribute("comment", comment);
				req.getRequestDispatcher("/page/good/comment.jsp").forward(req, resp);
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
