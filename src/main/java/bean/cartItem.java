package bean;

/**
 */
public class CartItem {
	private int goodId;
	private int num;

	public CartItem(int goodId, int num) {
		this.goodId = goodId;
		this.num = num;
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
