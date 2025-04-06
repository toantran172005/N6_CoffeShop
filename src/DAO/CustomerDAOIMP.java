package DAO;

import java.util.ArrayList;

import Models.Customers;
import Models.Products;

public class CustomerDAOIMP implements CustomerDAO {

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
	public boolean registerCustomer(Customers customer) {
		// TODO Auto-generated method stub
		return false;
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

}
