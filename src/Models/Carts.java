package Models;

import java.util.ArrayList;

public class Carts {
	private int cartID;
	private Customers customerID;
	private ArrayList<CartItems> listCartItem;

	public Carts() {
		super();
		listCartItem = new ArrayList<CartItems>();
	}

	public Carts(int cartID) {
		super();
		this.cartID = cartID;
	}

	public Carts(int cartID, Customers customerID, ArrayList<CartItems> listCartItem) {
		super();
		this.cartID = cartID;
		this.customerID = customerID;
		this.listCartItem = listCartItem;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public Customers getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customers customerID) {
		this.customerID = customerID;
	}

	public ArrayList<CartItems> getListCartItem() {
		return listCartItem;
	}

	public void setListCartItem(ArrayList<CartItems> listCartItem) {
		this.listCartItem = listCartItem;
	}

}
