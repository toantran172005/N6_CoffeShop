package Models;

import java.time.LocalDate;

public class Payments {
	private int paymentID;
	private Orders orderID;
	private double paymentTotal;
	private String paymentMethod;
	private LocalDate paymentDate;

	public Payments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payments(int paymentID, Orders orderID, double paymentTotal, String paymentMethod, LocalDate paymentDate) {
		super();
		this.paymentID = paymentID;
		this.orderID = orderID;
		this.paymentTotal = paymentTotal;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Orders getOrderID() {
		return orderID;
	}

	public void setOrderID(Orders orderID) {
		this.orderID = orderID;
	}

	public double getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(double paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

}
