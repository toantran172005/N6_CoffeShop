package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static Connection con = null;

	public static Connection getConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=N6_COFFESHOP;encrypt=true;trustServerCertificate=true";
		String username = "sa";
		String password = "T111375t";
		try {
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
