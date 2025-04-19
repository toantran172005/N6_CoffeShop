package Models;

import java.time.LocalDate;

public class Employees {
	private int employeeID;
	private String employeeName;
	private String phone;
	private String email;
	private String address;
	private LocalDate hiredDate;

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees(int employeeID) {
		super();
		this.employeeID = employeeID;
	}

	public Employees(int employeeID, String employeeName, String phone, String email, String address,
			LocalDate hiredDate) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.hiredDate = hiredDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(LocalDate hiredDate) {
		this.hiredDate = hiredDate;
	}

}
