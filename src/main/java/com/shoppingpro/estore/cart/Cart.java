package com.shoppingpro.estore.cart;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shoppingpro.estore.products.Product;
import com.shoppingpro.estore.user.User;


@Entity
@JsonIgnoreProperties({ "user"})
public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartPK id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name="product_id")
	private Product product;
	
	private Integer quantity;
	
	public Cart() {}
	
	public Cart(Integer userId, Integer productId, Integer quantity, User user, Product product) {
		this.id = new CartPK(userId, productId);
		this.quantity = quantity;
		this.product = product;
		this.user = user;
	}

	public CartPK getId() {
		return id;
	}

	public void setId(CartPK id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
