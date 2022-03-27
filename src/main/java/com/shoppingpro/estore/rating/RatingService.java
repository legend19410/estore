package com.shoppingpro.estore.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingpro.estore.cart.Cart;
import com.shoppingpro.estore.products.Product;
import com.shoppingpro.estore.products.ProductService;
import com.shoppingpro.estore.user.User;
import com.shoppingpro.estore.user.UserService;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	
	public List<Rating> getRatings() {
		return (List<Rating>) ratingRepository.findAll();
	}
	
	public Rating findById(Integer userId, Integer productId) {
		
//		//find user
//		User user = userService.findById(userId);
//		//find product
//		Product product = productService.findById(userId);
		
		Rating rating = ratingRepository.findById(new RatingPK(userId, productId)).get();
		return rating;
	}
	
	public Rating rateProduct(Integer userId, Integer productId, Integer rating) {
		//find user
		User user = userService.findById(userId);
		//find product
		Product product = productService.findById(productId);
		
		if(user != null || product != null) {
			
			Rating newRating = new Rating(userId, productId, user, product, rating);
			ratingRepository.save(newRating);
			return newRating;
		}
		return null;
	}
}
