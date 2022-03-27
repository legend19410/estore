package com.shoppingpro.estore.rating;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.shoppingpro.estore.products.Product;
import com.shoppingpro.estore.user.User;


@Entity
public class Rating implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatingPK id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name="product_id")
	private Product product;
	
	private Integer rating;
	
	public Rating() {}
	
	public Rating(Integer userId, Integer productId, User user, Product product, Integer rating) {
		this.id = new RatingPK(userId, productId);
		this.user = user;
		this.product = product;
		this.rating = rating;
	}

	public RatingPK getId() {
		return id;
	}

	public void setId(RatingPK id) {
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
}
