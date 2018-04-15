package service;

import bean.Order;
import dao.OrderDao;

import java.util.List;

/**
 */
public class OrderService {
	public static String STATUS_PAIED = "0";
	public static String STATUS_CONFIRMED = "1";
	public static String STATUS_CHECKED = "2";
	public static String STATUS_FINISH = "3";

	private OrderDao orderDao = new OrderDao();

	public int saveOrder(Order order) {
		return orderDao.insert(order);
	}

	public void updateOrderStatus(String status, int orderId) {
		orderDao.update(status, orderId);
	}

	public List<Order> finalUserOrders(int userId) {
		return orderDao.findAllByUserId(userId);
	}

	public List<Order> findAll(){
		return orderDao.findAll();
	}

}
