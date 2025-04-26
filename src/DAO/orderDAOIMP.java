package DAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.DatabaseConnection;
import Models.Customers;
import Models.Employees;
import Models.OrderDetails;
import Models.Orders;
import Models.Products;

public class orderDAOIMP implements orderDAO {

	private Connection con;

	public orderDAOIMP() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Orders> getAllOrderToday() {
	    ArrayList<Orders> orders = new ArrayList<>();
	    try {
//	         Lấy ngày hiện tại
	        LocalDate today = LocalDate.now();

	        String query = "SELECT OrderID, OrderDate, EmployeeID, TotalPrice FROM Orders " +
	                      "WHERE CAST(OrderDate AS DATE) = ? AND State = N'Đã xử lí'";
	        PreparedStatement pre = con.prepareStatement(query);

	        pre.setDate(1, java.sql.Date.valueOf(today));

	        ResultSet rs = pre.executeQuery();
	        while (rs.next()) {
	            int orderID = rs.getInt(1);
	            Timestamp timestamp = rs.getTimestamp(2);
	            Date orderDate = new Date(timestamp.getTime());
	            Employees emp = new Employees(rs.getInt(3));
	            double price = rs.getDouble(4);
	            Orders order = new Orders(orderID, emp, orderDate, price);
	            orders.add(order);
	        }
	        rs.close();
	        pre.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return orders;
	}

	public ArrayList<Orders> getUnprocessedOrdersByEmp(int empId) {
		ArrayList<Orders> list = new ArrayList<>();
		String sql = "SELECT * FROM Orders WHERE State = N'Đang xử lý'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int orderID = rs.getInt("OrderID");
				int customerID = rs.getInt("CustomerID");
				int employeeID = rs.getInt("EmployeeID");
				Date orderDate = rs.getDate("OrderDate");
				double totalPrice = rs.getDouble("TotalPrice");
				String state = rs.getString("State");

//				 Ánh xạ object từ ID
				Customers customer = new CustomerDAOIMP().getByID(customerID);
				Employees employee = new EmployeeDAOIMP().getByID(employeeID);
				Orders order = new Orders(orderID, customer, employee, orderDate, totalPrice, state);
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<OrderDetails> getOrderDetailsByOrderID(int orderID) {
		ArrayList<OrderDetails> list = new ArrayList<>();
		String sql = "SELECT od.OrderDetailID, od.OrderID, od.ProductID, od.Quantity, od.Price, "
				+ "p.ProductName, p.Price AS productPrice " + "FROM OrderDetails od "
				+ "JOIN Products p ON od.ProductID = p.ProductID " + "WHERE od.OrderID = ?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, orderID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				OrderDetails od = new OrderDetails();

				od.setOrderDetailID(rs.getInt("orderDetailID"));
				od.setQuantity(rs.getInt("quantity"));
				od.setPrice(rs.getDouble("price"));

				Orders order = new Orders();
				order.setOrderID(rs.getInt("orderID"));
				od.setOrderID(order);

				Products product = new Products();
				product.setProductID(rs.getInt("productID"));
				product.setPrice(rs.getDouble("productPrice"));
				product.setProductName(rs.getString("ProductName"));
				od.setProductID(product);

				list.add(od);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateOrderState(int orderID, int employeeID, String newState) {
		String sql = "UPDATE Orders SET State = ? , EmployeeID= ? WHERE OrderID = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, newState);
			stmt.setInt(2, employeeID);
			stmt.setInt(3, orderID);
			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean createStatistic(int employeeID, double totalPrice, int orderCount) {
	    try {
//	         Kiểm tra xem tồn tại chưa
	        String checkSta = "SELECT COUNT(*) FROM [dbo].[Statistics] "
	                + "WHERE EmployeeID = ? AND OrderCount = ? AND CAST(CreatedDate AS DATE) = CAST(GETDATE() AS DATE)";
	        PreparedStatement checkPre = con.prepareStatement(checkSta);
	        checkPre.setInt(1, employeeID);
	        checkPre.setInt(2, orderCount);
	        ResultSet rs = checkPre.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) > 0) {
//	             Đã tồn tại, không tạo
	            rs.close();
	            checkPre.close();
	            return false;
	        }
	        
	        rs.close();
	        checkPre.close();
	        
//	         Chưa tồn tại thì tạo
	        String createSta = "INSERT INTO [dbo].[Statistics]([EmployeeID], [TotalPrice], [OrderCount]) "
	                + "VALUES(?,?,?)";
	        PreparedStatement pre = con.prepareStatement(createSta);
	        pre.setInt(1, employeeID);
	        pre.setDouble(2, totalPrice);
	        pre.setInt(3, orderCount);
	        pre.executeUpdate();
	        pre.close();
	        
//	         Tạo file thống kê
	        ArrayList<Orders> allOrders = getAllOrderToday();
	        ArrayList<Orders> employeeOrders = new ArrayList<>();
	        
	        for (Orders ord : allOrders) {
	            if (ord.getEmployeeID().getEmployeeID() == employeeID) {
	                employeeOrders.add(ord);
	            }
	        }

	        EmployeeDAOIMP empDAO = new EmployeeDAOIMP();
	        Employees empl = empDAO.getByID(employeeID);
	        String empName = empl.getEmployeeName();
	        
	        LocalDate today = LocalDate.now();
	        String fileName = "ThongKe_" + employeeID + "_" + today + ".txt";
	        
	        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	        writer.write("\t   Nhóm 6 - The Coffee Shop\n");
	        writer.write("\t     Báo Cáo Thống Kê Ngày " + today + "\n\n");
	        writer.write("Nhân viên: " + empName + " (ID: " + employeeID + ")\n");
	        writer.write("Tổng số đơn hàng: " + orderCount + "\n");
	        writer.write(String.format("Tổng doanh thu: %,.2f₫\n", totalPrice));
	        writer.write("-------------------------------------------------\n");
	        writer.write(String.format("%-15s %-20s\n", "OrderID", "Tổng Tiền"));
	        writer.write("-------------------------------------------------\n");

	        for (Orders ord : employeeOrders) {
	            writer.write(String.format("%-15d %,.2f₫\n", ord.getOrderID(), ord.getTotalPrice()));
	        }

	        writer.write("-------------------------------------------------\n");
	        writer.close();
	        
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
