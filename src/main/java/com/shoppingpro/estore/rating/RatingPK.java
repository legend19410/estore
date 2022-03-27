package com.shoppingpro.estore.rating;


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class RatingPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	Integer userId;
	
	@Column(name="product_id")
	Integer productId;
	
	public RatingPK() {}
	
	public RatingPK(Integer userId, Integer productId) {
		this.userId = userId;
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

}