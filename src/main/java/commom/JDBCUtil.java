package commom;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作jdbc的工具类
 */
public class JDBCUtil {

	public static String DRIVERNAME = null;
	public static String URL = null;
	public static String USER = null;
	public static String PASSWORD = null;

	public static Connection conn = null;

	static {
		try {
//			获取配置文件
			Properties props = new Properties();
//			//Reader in = new FileReader("db.properties");
			InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream(".\\db.properties");
			props.load(in);

			DRIVERNAME = props.getProperty("drivername");
			URL = props.getProperty("url");
			USER = props.getProperty("user");
			PASSWORD = props.getProperty("password");
//			DRIVERNAME = "com.mysql.jdbc.Driver";
//			URL = "jdbc:mysql://localhost:3306/online_voting?useUnicode=true&amp;characterEncoding=utf-8";
//			USER = "root";
//			PASSWORD = "";
			System.out.println("------USE DB.PROPERTIES-----");
			System.out.println(DRIVERNAME + "\n"
					+ USER + "\n"
					+ URL + "\n"
					+ PASSWORD);
			System.out.println("-----------------------------");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 注册驱动、连接数据库
	 *
	 * @return Connection 对象
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		if (conn != null) {
			return conn;
		}

		Class.forName(DRIVERNAME);
		conn = DriverManager.getConnection(URL, USER, PASSWORD);

		return conn;
	}

	/**
	 * 关闭连接
	 *
	 * @param st
	 * @throws SQLException
	 */
	public static void closeResource(PreparedStatement st) {
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			Log.i("JDBCUtil", "error closeResource");
		}
	}

	/**
	 * 关闭连接
	 *
	 * @param rs
	 * @param st
	 * @throws SQLException
	 */
	public static void closeResource(ResultSet rs, PreparedStatement st) {
		try {
			rs.close();
			st.close();
		} catch (Exception e) {
			Log.i("JDBCUtil", e.toString());
		}
	}

}