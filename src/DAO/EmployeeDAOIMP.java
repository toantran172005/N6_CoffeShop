package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import Database.DatabaseConnection;
import Models.Customers;
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
