package com.shoppingpro.estore.cart;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shoppingpro.estore.products.Product;
import com.shoppingpro.estore.products.ProductService;
import com.shoppingpro.estore.user.User;
import com.shoppingpro.estore.user.UserService;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	
	public List<Cart> getCarts() {
		return (List<Cart>) cartRepository.findAll();
	}
	
	public User addToCart(Integer userId, Integer productId, Integer quantity) {
		//find user
		User user = userService.findById(userId);
		//find product
		Product product = productService.findById(productId);
		
		if(user != null || product != null) {
			
			Cart cartItem = new Cart(userId, productId, quantity, user, product);
			cartRepository.save(cartItem);
//			userService.update(user);
			return userService.findById(userId);
		}
		return null;
	}
	
	public User delete(Integer userId, Integer productId) {
		cartRepository.deleteById(new CartPK(userId, productId));
		return userService.findById(userId);
	}
	
	public User update(Integer userId, Integer productId, Map<String, Object> updatedParams) throws UsernameNotFoundException {
		Cart cart = cartRepository.findById(new CartPK(userId, productId)).orElseThrow(() -> 
			 new UsernameNotFoundException("user selected to be updated not found")
		);

		updatedParams.forEach((key, value)->{
			
			Field field = ReflectionUtils.findRequiredField(Cart.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, cart, value);
		});
		cartRepository.save(cart);
		return userService.findById(userId);
	}
}
