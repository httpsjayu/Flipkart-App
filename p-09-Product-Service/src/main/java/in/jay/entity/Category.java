package in.jay.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	private String categoryName;
	
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "category" ,cascade = CascadeType.ALL)
	private List<Product> prodcuts;


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public List<Product> getProdcuts() {
		return prodcuts;
	}


	public void setProdcuts(List<Product> prodcuts) {
		this.prodcuts = prodcuts;
	}
	
	

}
