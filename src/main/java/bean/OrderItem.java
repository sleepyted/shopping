package bean;

/**
 */
public class OrderItem {
	private Order order;
	private User user;
	private Good good;

	public OrderItem(Order order, User user, Good good) {
		this.order = order;
		this.user = user;
		this.good = good;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}
}
