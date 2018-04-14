package dao;

import bean.Attachment;
import bean.Good;
import common.JDBCUtil;
import common.Log;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class GoodDao extends BaseDao implements PubDefine {

	private static final String TAG = "GoodDao";

	public int insert(Good good){
		try{
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO shopping.good" +
					" (name, picId, discription, price, count)" +
					" VALUES" +
					" (?,?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, good.getName());
			ps.setInt(2, good.getPicId());
			ps.setString(3, good.getDiscription());
			ps.setDouble(4, good.getPrice());
			ps.setInt(5, good.getCount());

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

	public Good findById(int id){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,picId,name,discription, price,count FROM shopping.good" +
					" WHERE " +
					" id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Good good = new Good();
			if (rs.next()) {
				good.setId(rs.getInt("id"));
				good.setPicId(rs.getInt("picId"));
				good.setName(rs.getString("name"));
				good.setDiscription(rs.getString("discription"));
				good.setPrice(rs.getDouble("price"));
				good.setCount(rs.getInt("count"));
				return good;
			}
			return null;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public List<Good> findAllGood(){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,picId,name,discription, price, count FROM shopping.good";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List goodList = new ArrayList<>();
			while (rs.next()) {
				Good good = new Good();
				good.setId(rs.getInt("id"));
				good.setPicId(rs.getInt("picId"));
				good.setName(rs.getString("name"));
				good.setDiscription(rs.getString("discription"));
				good.setPrice(rs.getDouble("price"));
				good.setCount(rs.getInt("count"));
				goodList.add(good);
			}
			return goodList;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public void del(int id){
		try{
			conn = JDBCUtil.getConnection();
			String sql = "DELETE  FROM shopping.good WHERE id = ?";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
		} catch (Exception e) {
			Log.i("GoodDao", e.toString());
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public void update(Good good){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE shopping.good SET good.name= ? , good.discription = ? , good.price = ? , good.count = ?, good.picId=? WHERE good.id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,good.getName());
			ps.setString(2, good.getDiscription());
			ps.setDouble(3, good.getPrice());
			ps.setInt(4, good.getCount());
			ps.setInt(5, good.getPicId());
			ps.setInt(6, good.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			Log.i(TAG, e.toString());
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}


}
