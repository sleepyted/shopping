package bean;

import java.util.List;

/**
 */
public class Result<T> {
	private int status;
	private String msg;
	private List<T> list;
	private Class<T> bean;

	public Result() {
	}

	public Result(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Class<T> getBean() {
		return bean;
	}

	public void setBean(Class<T> bean) {
		this.bean = bean;
	}
}
