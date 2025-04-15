package DAO;

import Models.Customers;
import Models.Employees;
import Models.Products;
import Models.Orders;

import java.util.ArrayList;

public interface EmployeeDAO {
	void addProduct(Products product); // Thêm sản phẩm

	void updateProduct(Products product); // Sửa sản phẩm

	void deleteProduct(int productId); // Xóa sản phẩm

	ArrayList<Orders> getAllOrders(); // Xem danh sách đơn hàng

	void updateOrderStatus(int orderId, String status); // Cập nhật trạng thái đơn hàng

	ArrayList<Customers> getAllCustomers(); // Xem danh sách khách hàng
	
	Employees getEmployee(int id); // Lấy 1 nhân viên
}