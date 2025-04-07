package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import Database.DatabaseConnection;
import Models.Customers;
import Models.Products;

public class CustomerDAOIMP implements CustomerDAO {
	private Connection con;
	
	public CustomerDAOIMP() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	public Customers loginCustomer(String email, String password) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int registerCustomer(String cusName, String email, String pwd, String phone, String address) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void closeDatabase() {
		DatabaseConnection.closeConnection(con);
		System.out.println("Closed the connection to database!");
	}

}
