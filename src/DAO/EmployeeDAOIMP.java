package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Database.DatabaseConnection;
import Models.Employees;
import Models.Orders;
import Models.ProductType;
import Models.Products;

public class EmployeeDAOIMP implements EmployeeDAO {
	
	private Connection con;

//	Khởi tạo mặc định kết nối với database
	public EmployeeDAOIMP() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	Đóng ứng dụng thì ngắt kết nối
	public void closeDatabase() {
		DatabaseConnection.closeConnection(con);
		System.out.println("Closed the connection to database!");
	}

	@Override
	public boolean addProduct(Products product) {
		String sql = "INSERT INTO Products (productName, price, description, size, quantity, productImg, productTypeID) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (
	        PreparedStatement ps = con.prepareStatement(sql);
	    ) {
	    	ps.setString(1, product.getProductName());
	    	ps.setDouble(2, product.getPrice());
	    	ps.setString(3, product.getDescription());
	    	ps.setString(4, product.getSize());
	    	ps.setInt(5, product.getQuantity());
	    	ps.setString(6, product.getProductImg());
	    	ps.setInt(7, product.getProductTypeID().getProductTypeID()); 

	        int result = ps.executeUpdate();
	        return result > 0; // Nếu thêm thành công thì trả về true
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean updateProduct(Products product) {
	    // Sử dụng PreparedStatement để cập nhật thông tin sản phẩm
	    String query = "UPDATE Products SET ProductName = ?, Price = ?, Description = ?, Size = ?, Quantity = ? WHERE ProductID = ?";
	    try (PreparedStatement stmt = con.prepareStatement(query)) { // Giả sử `conn` đã được kết nối từ bên ngoài

	        stmt.setString(1, product.getProductName());
	        stmt.setDouble(2, product.getPrice());
	        stmt.setString(3, product.getDescription());
	        stmt.setString(4, product.getSize());
	        stmt.setInt(5, product.getQuantity());
	        stmt.setInt(6, product.getProductID()); // Giả sử mỗi sản phẩm có ID

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteProduct(int productID) {
	    try {
	        String sql = "DELETE FROM Products WHERE productID = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, productID);
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	        return false;
	}

	@Override
	public ArrayList<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrderStatus(int orderId, String status) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Employees getEmployee(int id) {
		Employees emp = new Employees();
		try {
			String sql = "SELECT EmployeeName, Phone, Email, Address, HiredDate From Employees WHERE EmployeeID = ?";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				emp.setEmployeeID(id);
				emp.setEmployeeName(rs.getString(1));
				emp.setPhone(rs.getString(2));
				emp.setEmail(rs.getString(3));
				emp.setAddress(rs.getString(4));
				Date HiredDate = rs.getDate(5);
				LocalDate date = HiredDate.toLocalDate();
				emp.setHiredDate(date);
			}
			pre.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public ArrayList<Products> getListProductFromDb() {
		Statement sta = null;
		ResultSet rs = null;
		ArrayList<Products> listProduct = new ArrayList<>();
		String sql = "SELECT ProductID, ProductName, ProductTypeID, Price, Quantity, Description, Size, ProductIMG FROM Products "
				+ "WHERE ProductID % 2 = 0" + "OR ProductID IN (17,19)";
		try {
			 sta = con.createStatement();
			 rs = sta.executeQuery(sql);
			while (rs.next()) {
				Products pro = new Products();
				pro.setProductID(rs.getInt(1));
				pro.setProductName(rs.getString(2));
				ProductType type = new ProductType(rs.getInt(3));
				pro.setProductTypeID(type);
				pro.setPrice(rs.getBigDecimal(4).doubleValue());
				pro.setQuantity(rs.getInt(5));
				pro.setDescription(rs.getString(6));
				pro.setSize(rs.getString(7));
				pro.setProductImg(rs.getString(8));
				listProduct.add(pro);
			}
			sta.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProduct;

	}

	@Override
	public int updateEmployeeInfo(Employees employees) {
		String[] regex = { "^[\\p{L} ]{2,}$", "^(?!_|\\.)[\\w.+%-]+@[a-zA-Z0-9.-]{2,}(\\.[a-z]{2,})+$", "^[0-9]{10}$",
		"^[\\p{L}0-9 /.-]{2,}$" };
		if (!Pattern.matches(regex[0], employees.getEmployeeName()))
			return 1; // sai tên
		if (!Pattern.matches(regex[1], employees.getEmail()))
			return 2; // sai email
		if (!Pattern.matches(regex[2], employees.getPhone()))
			return 3; // sai phone
		if (!Pattern.matches(regex[3], employees.getAddress()))
			return 4; // sai địa chỉ

		try {
			String update = "UPDATE Employees set EmployeeName = ?, Phone = ?, Email = ?, Address = ? "
							+ "WHERE EmployeeID = ?";
			PreparedStatement pre = con.prepareStatement(update);
			pre.setString(1, employees.getEmployeeName());
			pre.setString(2, employees.getPhone());
			pre.setString(3, employees.getEmail());
			pre.setString(4, employees.getAddress());
			pre.setInt(5, employees.getEmployeeID());
			pre.executeUpdate();
				pre.close();
				return 0;
	
} catch (SQLException e) {
	return -2; // Lỗi trả về -2
}
	}
	public Employees getByID(int id) {
        Employees emp = null;
        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";

        try (
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	emp = new Employees();
                emp.setEmployeeID(rs.getInt("employeeID"));
                emp.setEmployeeName(rs.getString("employeeName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }
}
