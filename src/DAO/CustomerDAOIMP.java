package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Database.DatabaseConnection;
import Models.Customers;
import Models.Products;

public class CustomerDAOIMP implements CustomerDAO {
	private Connection con;

//	Khởi tạo mặc định kết nối với database
	public CustomerDAOIMP() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

//	Đóng ứng dụng thì ngắt kết nối
	public void closeDatabase() {
		DatabaseConnection.closeConnection(con);
		System.out.println("Closed the connection to database!");
	}

	@Override
	public void addProductToCart(int customerId, Products product, int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Products> searchProducts(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int loginCustomer(String email, String password) {
		try {
//			Kiểm tra email tồn tại chưa?
			String checkEmail = "SELECT * FROM Customers WHERE Email = ?";
			PreparedStatement preEmail = con.prepareStatement(checkEmail);
			preEmail.setString(1, email);
			ResultSet re = preEmail.executeQuery();
			if (re.next()) {
//				Kiểm tra mật khẩu
				String pwd = re.getString("Password");
				if (pwd.equals(password))
					return 0; // Đăng nhập thành công
				else
					return 2; // Sai mật khẩu
			} else
				return 1; // sai email

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public int registerCustomer(String cusName, String email, String pwd, String phone, String address) {
		// 1: cusName, 2: email, 3: password, 4: phone, 5 address
		String[] regex = { "^[\\p{L} ]{2,}$", "^(?!_|\\.)[\\w.+%-]+@[a-zA-Z0-9.-]{2,}(\\.[a-z]{2,})+$",
				"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$", "^[0-9]{10}$", "^[\\p{L}0-9 /.-]{2,}$" };
		if (!Pattern.matches(regex[0], cusName))
			return 1; // sai tên
		if (!Pattern.matches(regex[1], email))
			return 2; // sai email
		if (!Pattern.matches(regex[2], pwd))
			return 3; // sai password
		if (!Pattern.matches(regex[3], phone))
			return 4; // sai phone
		if (!Pattern.matches(regex[4], address))
			return 5; // sai địa chỉ

		try {
//			Kiểm tra email tồn tại chưa?
			String checkEmail = "SELECT 1 FROM Customers WHERE Email = ?";
			PreparedStatement preEmail = con.prepareStatement(checkEmail);
			preEmail.setString(1, email);
			ResultSet re = preEmail.executeQuery();
			if (re.next()) {
				preEmail.close();
				re.close();
				return -1; // email tồn tại trả về -1
			}
//			Email chưa tồn tại
			String sql = "INSERT INTO Customers(CustomerName,Phone,Email,Password,Address) VALUES (?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, cusName);
			pre.setString(2, phone);
			pre.setString(3, email);
			pre.setString(4, pwd);
			pre.setString(5, address);
			pre.executeUpdate();
			pre.close();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2; // lỗi trả về -2
		}

	}

	@Override
	public String forgotPassword(String email) {
		String sql = "SELECT Password From Customers WHERE Email = ?";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, email);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) { // Tìm thấy Email
				return rs.getString(1);
			} else
				return null; // Không tìm thấy email
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void logoutCustomer(int customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateCustomerInfo(Customers customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean payOrder(int customerId) {
		// TODO Auto-generated method stub
		return false;
	}

}
