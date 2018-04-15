package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 */
public class BaseDao  implements PubDefine {
    protected Connection conn = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;
}
