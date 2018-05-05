package dao;

import bean.Comment;
import common.JDBCUtil;
import common.Log;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class CommentDao extends BaseDao {
	private static final String TAG = "CommentDao";

	public int insert(Comment comment) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO shopping.comments" +
					" (goodId, userId, username, content)" +
					" VALUES" +
					" (?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, comment.getGoodId());
			ps.setInt(2, comment.getUserId());
			ps.setString(3, comment.getUsername());
			ps.setString(4, comment.getContent());

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

	public List<Comment> findByGoodId(int id){
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT id,goodId,userId,content,username,create_date FROM shopping.comments WHERE goodId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List<Comment> comments = new ArrayList<>();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUserId(rs.getInt("userId"));
				comment.setGoodId(rs.getInt("goodId"));
				comment.setContent(rs.getString("content"));
				comment.setUsername(rs.getString("username"));
				comment.setCreateDate(rs.getTimestamp("create_date"));
				comments.add(comment);
			}
			return comments;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return null;
		} finally {
			JDBCUtil.closeResource(rs, ps);
		}
	}
}
