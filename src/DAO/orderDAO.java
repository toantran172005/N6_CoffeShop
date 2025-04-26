package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;
import Models.Customers;
import Models.Employees;
import Models.OrderDetails;
import Models.Orders;
import Models.Products;

public class orderDAO {
	
private Connection con;
public orderDAO() {
	try {
		con = DatabaseConnection.getConnection();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public List<Orders> getUnprocessedOrdersByEmp(int empId) {
        List<Orders> list = new ArrayList<>();
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

                // Ánh xạ object từ ID
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

	   public List<OrderDetails> getOrderDetailsByOrderID(int orderID) {
	        List<OrderDetails> list = new ArrayList<>();
	        String sql = "SELECT od.OrderDetailID, od.OrderID, od.ProductID, od.Quantity, od.Price, " +
	                 "p.ProductName, p.Price AS productPrice " +
	                 "FROM OrderDetails od " +
	                 "JOIN Products p ON od.ProductID = p.ProductID " +
	                 "WHERE od.OrderID = ?";

	        try (PreparedStatement stmt = con.prepareStatement(sql)) {

	            stmt.setInt(1, orderID);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                OrderDetails od = new OrderDetails();

	                // Set order detail
	                od.setOrderDetailID(rs.getInt("orderDetailID"));
	                od.setQuantity(rs.getInt("quantity"));
	                od.setPrice(rs.getDouble("price"));

	                // Set order
	                Orders order = new Orders();
	                order.setOrderID(rs.getInt("orderID"));
	                od.setOrderID(order);

	                // Set product
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
	   
	   public boolean updateOrderState(int orderID,int employeeID, String newState) {
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
}
