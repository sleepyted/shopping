package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by matengfei1 on 2018/4/10.
 */
public class BaseDao {
    protected Connection conn = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;
}
