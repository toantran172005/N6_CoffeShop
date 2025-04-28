package DAO;

import java.util.ArrayList;

import Models.OrderDetails;
import Models.Orders;

public interface orderDAO {

	public ArrayList<Orders> getAllOrderToday();

	public ArrayList<Orders> getUnprocessedOrdersByEmp(int empId);

	public ArrayList<OrderDetails> getOrderDetailsByOrderID(int orderID);

	public boolean updateOrderState(int orderID, int employeeID, String newState);

	public boolean createStatistic(int employeeID, double totalPrice, int orderCount);
	
	boolean deleteOrder(int orderID); 


}
