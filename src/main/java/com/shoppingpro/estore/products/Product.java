package com.shoppingpro.estore.products;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shoppingpro.estore.cart.Cart;
import com.shoppingpro.estore.category.Category;

@Entity
@JsonIgnoreProperties({"carts", "category"})
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double price;
	private String unit;
	private int quantity;
	private String image;
	
	@OneToMany(mappedBy="user")
	private List<Cart> carts;
	
	@ManyToOne
	private Category category;
	
	public Product() {}
	
	
	public Product(int id, String name, double price, String unit, int quantity, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.quantity = quantity;
		this.image = image;
	}

	
	
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Cart> getCarts() {
		return carts;
	}


	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", unit=" + unit + ", quantity=" + quantity
				+ "]";
	}
	
	
}
