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
					" (name, picId, discription, price)" +
					" VALUES" +
					" (?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, good.getName());
			ps.setInt(2, good.getPicId());
			ps.setString(3, good.getDiscription());
			ps.setDouble(4, good.getPrice());

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
			String sql = "SELECT id,picId,name,discription, price FROM shopping.good" +
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
			String sql = "SELECT id,picId,name,discription, price FROM shopping.good";
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

}
