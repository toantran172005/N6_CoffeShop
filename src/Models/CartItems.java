package Models;

public class CartItems {
	private int cartItemID;
	private Carts cartID;
	private Products product;
	private int quantity;

	public CartItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItems(int cartItemID, Carts cartID, Products product, int quantity) {
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

	public Carts getCartID() {
		return cartID;
	}

	public void setCartID(Carts cartID) {
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
