package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.UIManager;

import Database.DatabaseConnection;

public class Run {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Connection con = DatabaseConnection.getConnection();
			
			String sql = "Insert into Customers(CustomerName, Phone, Email, Password, Address)"
					+ "Values (N\'Test Insert\',N\'0909090987\',N\'test@gmail.com\',N\'testabc\',N\'Hồ Chí Minh\' )";
			Statement stat = con.createStatement();
			int check = stat.executeUpdate(sql);
			if(check >0)
				System.out.println("Insert successfully!");
			else System.out.println("Failed to insert!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
