package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;
import Frames.customerFrame;
import Models.Customers;
import Models.Employees;
import Models.Orders;

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
}
