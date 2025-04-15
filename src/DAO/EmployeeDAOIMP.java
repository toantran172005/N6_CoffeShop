package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.DatabaseConnection;
import Models.Customers;
import Models.Employees;
import Models.Orders;
import Models.Products;

public class EmployeeDAOIMP implements EmployeeDAO {
	private Connection con = DatabaseConnection.getConnection();

	public EmployeeDAOIMP() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public Employees getEmployee(int employeeID) {
		Employees emp = new Employees();
		try {
			String sql = "SELECT EmployeeName, Phone, Email, Address From Employees WHERE EmployeeID = ?";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, employeeID);
			ResultSet rs = pre.executeQuery();
			if(rs.next()) {
				emp.setEmployeeID(employeeID);
				emp.setEmployeeName(rs.getString(1));
				emp.setPhone(rs.getString(2));
				emp.setEmail(rs.getString(3));
				emp.setAddress(rs.getString(4));
				emp.setHiredDate(LocalDate.now());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public void addProduct(Products product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProduct(Products product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub

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
	public ArrayList<Customers> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void closeDatabase() {
		DatabaseConnection.closeConnection(con);
		System.out.println("Closed the connection to database!");
	}

}
