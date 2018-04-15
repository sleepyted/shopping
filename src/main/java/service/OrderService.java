package service;

import bean.Good;
import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.GoodDao;
import dao.OrderDao;
import dao.UserDao;

import java.util.ArrayList;
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
		int count = order.getNum();
		GoodDao goodDao = new GoodDao();
		Good good = goodDao.findById(order.getGoodId());
		good.setCount(good.getCount() - count);
		goodDao.update(good);
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

	public Order findById(int id){
		return orderDao.findById(id);
	}
	public List<OrderItem> getOrderItem(List<Order> orders) {
		UserDao userDao = new UserDao();
		GoodDao goodDao = new GoodDao();
		List<OrderItem> orderItems = new ArrayList<>();
		for(int i=0;i<orders.size();i++) {
			User user = userDao.getUserById(orders.get(i).getUserId());
			Good good = goodDao.findById(orders.get(i).getGoodId());
			OrderItem orderItem = new OrderItem(orders.get(i),user, good);
			orderItems.add(orderItem);
		}
		return orderItems;
	}

}
