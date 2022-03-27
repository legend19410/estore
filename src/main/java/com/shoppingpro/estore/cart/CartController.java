package com.shoppingpro.estore.cart;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingpro.estore.user.User;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/")
	public List<Cart> getCarts(){
		
		cartService.getCarts().forEach(item->{
			System.out.println("");
		});
		
		return cartService.getCarts();
	}
	
	@GetMapping("add-to-cart")
	public User addToCart(@RequestParam("user") Integer userId, 
						  @RequestParam("product") Integer productId,
						  @RequestParam Integer quantity) {
		return cartService.addToCart(userId, productId, quantity);
	}
	
	@GetMapping("/delete")
	public User delete( @RequestParam("user") Integer userId, 
						  @RequestParam("product") Integer productId){
		return cartService.delete(userId, productId); 
	}
	
	@PatchMapping("/{userId}/{productId}")
	public User update(@PathVariable Integer userId,
					   @PathVariable Integer productId,
					   @RequestBody Map<String, Object> updatedParams) throws UsernameNotFoundException{
		return cartService.update(userId, productId, updatedParams);
	}
	
}
