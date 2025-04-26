package Models;

import java.sql.Date;

public class Statistics {
	private int statisticID;
	private Employees employeeID;
	private double totalPrice;
	private int orderCount;
	private Date createDate;

	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Statistics(int statisticID, Employees employeeID, double totalPrice, int orderCount, Date createDate) {
		super();
		this.statisticID = statisticID;
		this.employeeID = employeeID;
		this.totalPrice = totalPrice;
		this.orderCount = orderCount;
		this.createDate = createDate;
	}

	public int getStatisticID() {
		return statisticID;
	}

	public void setStatisticID(int statisticID) {
		this.statisticID = statisticID;
	}

	public Employees getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employees employeeID) {
		this.employeeID = employeeID;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
