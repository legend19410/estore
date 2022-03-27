package com.shoppingpro.estore.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingpro.estore.user.User;



@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/")
	public List<Rating> getRatings(){
		
		return ratingService.getRatings();
	}
	
	@GetMapping("/{userId}/{productId}")
	public Rating getRating(@PathVariable Integer userId,
					   @PathVariable Integer productId
					   ){
		return ratingService.findById(userId, productId);
	}
	
	@GetMapping("/rate-product")
	public Rating rateProdct(@RequestParam("userId") Integer userId, 
						  @RequestParam("productId") Integer productId,
						  @RequestParam Integer rating) {
		return ratingService.rateProduct(userId, productId, rating);
	}
}
