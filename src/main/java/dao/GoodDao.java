package dao;

import bean.Attachment;
import bean.Good;
import common.JDBCUtil;
import common.Log;

import java.sql.Statement;

/**
 */
public class GoodDao extends BaseDao implements PubDefine {


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
			return STATUS_ERROR;
		} catch (Exception e) {
			Log.i("GoodDao", e.toString());
			return STATUS_ERROR;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

}
