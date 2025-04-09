package Models;

public class Products {
	private int productID;
	private String productName;
	private String productType;
	private double price;
	private int quantity;
	private String description;
	private String size;
	private String productImg;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(int productID, String productName, String productType, double price, int quantity,
			String description, String size, String productImg) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productType = productType;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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
