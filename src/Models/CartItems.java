package Models;

public class CartItems {
	private int cartItemID;
	private int cartID;
	private Products product;
	private int quantity;

	public CartItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItems(int cartItemID, int cartID, Products product, int quantity) {
		super();
		this.cartItemID = cartItemID;
		this.cartID = cartID;
		this.product = product;
		this.quantity = quantity;
	}

	public int getCartItemID() {
		return cartItemID;
	}

	public void setCartItemID(int cartItemID) {
		this.cartItemID = cartItemID;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
