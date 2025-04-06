package DAO;

import java.util.ArrayList;

import Models.Customers;
import Models.Products;

public interface CustomerDAO {
	void addProductToCart(int customerId, Products product, int quantity); // Thêm sản phẩm vào giỏ hàng

	ArrayList<Products> searchProducts(String productName); // Tìm kiếm sản phẩm theo tên

	boolean registerCustomer(Customers customer); // Đăng ký tài khoản khách hàng

	Customers loginCustomer(String email, String password); // Đăng nhập tài khoản khách hàng

	void logoutCustomer(int customerId); // Đăng xuất tài khoản

	boolean updateCustomerInfo(Customers customer); // Cập nhật thông tin khách hàng

	boolean deleteCustomer(int customerId); // Xóa tài khoản khách hàng
	
	boolean payOrder(int customerId); // Thanh toán hóa đơn 
}