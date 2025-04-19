package Models;

public class ProductType {
	private int productTypeID;
	private String typeName;

	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductType(int productTypeID) {
		super();
		this.productTypeID = productTypeID;
	}

	public ProductType(int productTypeID, String typeName) {
		super();
		this.productTypeID = productTypeID;
		this.typeName = typeName;
	}

	public int getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(int productTypeID) {
		this.productTypeID = productTypeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
