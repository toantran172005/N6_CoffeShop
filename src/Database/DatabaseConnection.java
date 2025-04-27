package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection getConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=N6_COFFEESHOP;encrypt=true;trustServerCertificate=true";
		String username = "lynn";
		String password = "12345";
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
