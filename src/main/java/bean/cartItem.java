package bean;

/**
 */
public class CartItem {
	Good good = new Good();
	private int num;

	public CartItem(Good good, int num) {
		this.good = good;
		this.num = num;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
