package dao;

import bean.User;
import commom.JDBCUtil;
import commom.Log;
import commom.Util;

import java.sql.*;

/**
 * 数据访问--用户（User）
 */
public class UserDao extends BaseDao implements PubDefine {
	private static final String TAG = "UserDao";

	public int insert(User user) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO shopping.user" +
					" (username, email, tel, password, gender, user_type)" +
					" VALUES" +
					" (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getTel());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getGender());
			ps.setString(6, user.getUserType());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				return id;
			}
			return STATUS_ERROR;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return STATUS_ERROR;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public User getUserByEmailPassword(String email, String password) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,username, email, tel, gender, user_type, del_flag FROM shopping.user" +
					" WHERE " +
					" email = ? and password = ?";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, email);
			ps.setString(2, Util.EncoderByMd5(password));

			rs = ps.executeQuery();
			User user = new User();
			return resolveResultSet(rs, user);
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	public User getUserById(int id) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,username, email, tel, gender, user_type, del_flag FROM shopping.user" +
					" WHERE " +
					" id = ?";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			User user = new User();
			return resolveResultSet(rs, user);
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}

	private User resolveResultSet(ResultSet rs, User user) throws SQLException {
		if (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setGender(rs.getString("gender"));
			user.setTel(rs.getString("tel"));
			user.setUserType(rs.getString("user_type"));
			user.setDelFlag( rs.getString("del_flag"));
		}
		return user;
	}

	public Boolean getUserByEmail(String email) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,username, email, tel, gender, user_type, del_flag FROM shopping.user" +
					" WHERE " +
					" email = ?";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, email);
			rs = ps.executeQuery();
			User user = new User();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return false;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
		return false;
	}

}
