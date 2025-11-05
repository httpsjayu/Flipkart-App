package in.jay.dto;

public class ProductDto {
	
	private Integer productId;
	private String productName;
	private Double price;
	private Integer stock;
	private String description;
	private Integer discount;
	private Double priceBeforeDiscount;
	private String productImage;
	
	private Integer categoryId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Double getPriceBeforeDiscount() {
		return priceBeforeDiscount;
	}

	public void setPriceBeforeDiscount(Double priceBeforeDiscount) {
		this.priceBeforeDiscount = priceBeforeDiscount;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	

}
