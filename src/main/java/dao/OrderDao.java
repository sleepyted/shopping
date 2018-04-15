package dao;

import bean.Order;
import common.JDBCUtil;
import common.Log;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class OrderDao extends BaseDao{
	private static final String TAG = "OrderDao";

	public int insert(Order order){
		try{
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO shopping.orders (userId,goodId, status, tel, addr, num, name) VALUES (?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,order.getUserId());
			ps.setInt(2, order.getGoodId());
			ps.setString(3, order.getStatus());
			ps.setString(4, order.getTel());
			ps.setString(5, order.getAddr());
			ps.setInt(6, order.getNum());
			ps.setString(7,order.getName());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				return id;
			}
			return STATUS_NOT_FOUND;
		} catch (Exception e) {
			Log.i("GoodDao", e.toString());
			return STATUS_ERROR;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public Order findById(int id){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,userId,goodId,status,tel,addr,create_date,num,name FROM shopping.orders WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Order order = new Order();
			if (rs.next()) {
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("userId"));
				order.setGoodId(rs.getInt("goodId"));
				order.setStatus(rs.getString("status"));
				order.setTel(rs.getString("tel"));
				order.setAddr(rs.getString("addr"));
				order.setCreateDate(rs.getTimestamp("creat_date"));
				order.setNum(rs.getInt("num"));
				order.setName(rs.getString("name"));
				return order;
			}
			return null;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public List<Order> findAll(){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,userId,goodId,status,tel,addr,create_date,name FROM shopping.orders";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List orderList = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("userId"));
				order.setGoodId(rs.getInt("goodId"));
				order.setStatus(rs.getString("status"));
				order.setTel(rs.getString("tel"));
				order.setAddr(rs.getString("addr"));
				order.setCreateDate(rs.getTimestamp("creat_date"));
				order.setName(rs.getString("name"));
				orderList.add(order);
			}
			return orderList;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public List<Order> findAllByUserId(int id){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,userId,goodId,status,tel,addr,create_date,name FROM shopping.orders WHERE userId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List orderList = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("userId"));
				order.setGoodId(rs.getInt("goodId"));
				order.setStatus(rs.getString("status"));
				order.setTel(rs.getString("tel"));
				order.setAddr(rs.getString("addr"));
				order.setCreateDate(rs.getTimestamp("creat_date"));
				order.setName(rs.getString("name"));
				orderList.add(order);
			}
			return orderList;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public void update(String status, int orderId){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE shopping.orders SET orders.status = ? WHERE orders.id = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, orderId);

			ps.executeUpdate();
		} catch (Exception e) {
			Log.i(TAG, e.toString());
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}
}
