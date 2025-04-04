package Models;

import java.util.ArrayList;

public class Carts {
	private int cartID;
	private int customerID;
	private ArrayList<CartItems> listCartItem;

	public Carts() {
		super();
		listCartItem = new ArrayList<CartItems>();
	}

	public Carts(int cartID, int customerID, ArrayList<CartItems> listCartItem) {
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

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public ArrayList<CartItems> getListCartItem() {
		return listCartItem;
	}

	public void setListCartItem(ArrayList<CartItems> listCartItem) {
		this.listCartItem = listCartItem;
	}

}
