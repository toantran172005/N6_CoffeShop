package Models;

public class Products {
	private int productID;
	private String productName;
	private ProductType productTypeID;
	private double price;
	private int quantity;
	private String description;
	private String size;
	private String productImg;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(int productID) {
		super();
		this.productID = productID;
	}

	public Products(int productID, String productName, ProductType productTypeID, double price, int quantity,
			String description, String size, String productImg) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productTypeID = productTypeID;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.size = size;
		this.productImg = productImg;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductType getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(ProductType productTypeID) {
		this.productTypeID = productTypeID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

}
