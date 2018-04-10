package dao;

import bean.Attachment;
import bean.User;
import commom.JDBCUtil;
import commom.Log;

import java.sql.Statement;

/**
 */
public class AttachmentDao extends BaseDao implements PubDefine{
    private static String TAG = "AttachmentDao";

    public int insert(Attachment attachment){
        try{
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO shopping.attachment" +
                    " (origin_name, local_name)" +
                    " VALUES" +
                    " (?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, attachment.getOriginName());
            ps.setString(2, attachment.getLocalName());

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

    public Attachment getAttachmentById(int id){
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT id,origin_name,local_name, create_date FROM shopping.attachment" +
                    " WHERE " +
                    " id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Attachment attachment = new Attachment();
            if (rs.next()) {
                attachment.setId(rs.getInt("id"));
                attachment.setOriginName(rs.getString("origin_name"));
                attachment.setLocalName(rs.getString("local_name"));
                attachment.setCreateDate(rs.getTimestamp("create_date"));
                return attachment;
            }
            return null;
        } catch (Exception e) {
            Log.i(TAG, e.toString());
            return null;
        } finally {
            JDBCUtil.closeResource(rs, ps);
        }
    }
}
