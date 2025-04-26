package Models;

import java.sql.Date;

public class Orders {
	private int orderID;
	private Customers customerID;
	private Employees employeeID;
	private Date orderDate;
	private double totalPrice;
	private String state;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(int orderID) {
		super();
		this.orderID = orderID;
	}

	public Orders(int orderID,Employees employeeID, Date orderDate, double totalPrice) {
		super();
		this.orderID = orderID;
		this.employeeID = employeeID;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
	}

	public Orders(Customers customerID, Employees employeeID, double totalPrice) {
		super();
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.totalPrice = totalPrice;
	}

	public Orders(int orderID, Customers customerID, Employees employeeID, Date orderDate, double totalPrice,
			String state) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.state = state;
	}



	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customers getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customers customerID) {
		this.customerID = customerID;
	}

	public Employees getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employees employeeID) {
		this.employeeID = employeeID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
