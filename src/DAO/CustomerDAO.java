package DAO;

import java.util.ArrayList;

import Models.CartItems;
import Models.Customers;
import Models.Orders;
import Models.Products;

public interface CustomerDAO {
	int addProductToCart(int customerId, Products product, int quantity); // Thêm sản phẩm vào giỏ hàng

	int registerCustomer(String cusName, String email, String pwd, String phone, String address); // Đăng ký tài khoản

	int loginCustomer(String email, String password); // Đăng nhập tài khoản khách hàng

	String forgotPassword(String email); // Quên mật khẩu

	int updateCustomerInfo(Customers customer); // Cập nhật thông tin khách hàng
	
	ArrayList<Products> getListProductFromDb(); // Lấy sản phẩm từ database
	
	boolean isCustomer(int id); // Kiểm tra có phải customer
	
	Customers getCustomer(int customerID); // lấy 1 khách hàng
	
	ArrayList<CartItems> getCartItemsByCustomerID(int customerID); // Lấy list sản phẩm
	
	void updateCartItem(int customerID, Products product,int quantity); // Cập nhập số lượng cart item
	
	void deleteCartItem(int customerID, Products product); // Xóa sản phẩm
	
	void createOrder(Orders orderm, ArrayList<CartItems> list, String paymentMethod); // Tạo hóa đơn
	
	void clearCart(int customerID, ArrayList<CartItems> list); // Xóa hết sản phẩm trong giỏ hàng
	
	public Customers getByID(int id);

}