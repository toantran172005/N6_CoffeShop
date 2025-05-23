package DAO;

import Models.Employees;
import Models.Products;
import Models.Orders;

import java.util.ArrayList;

public interface EmployeeDAO {
	boolean addProduct(Products product); // Thêm sản phẩm

	 boolean updateProduct(Products product); // Sửa sản phẩm

	 boolean deleteProduct(int productId); // Xóa sản phẩm

	ArrayList<Orders> getAllOrders(); // Xem danh sách đơn hàng

	void updateOrderStatus(int orderId, String status); // Cập nhật trạng thái đơn hàng
	
	Employees getEmployee(int id); // Lấy 1 nhân viên
	
	 ArrayList<Products> getListProductFromDb();
	 
	 int updateEmployeeInfo(Employees employees); //sua thong tin nhan vien
}