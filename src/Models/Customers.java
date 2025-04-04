package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Customers {
	private int customerID;
	private String customerName;
	private String password;
	private String phone;
	private String email;
	private String address;
	private LocalDate CreateDate;

	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customers(int customerID, String customerName, String password, String phone, String email, String address,
			LocalDate createDate) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		CreateDate = createDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDate getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(LocalDate createDate) {
		CreateDate = createDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CreateDate, address, customerID, customerName, email, password, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;
		return Objects.equals(CreateDate, other.CreateDate) && Objects.equals(address, other.address)
				&& customerID == other.customerID && Objects.equals(customerName, other.customerName)
				&& Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone);
	}

}
