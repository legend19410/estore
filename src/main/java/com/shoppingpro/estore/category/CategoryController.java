package com.shoppingpro.estore.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingpro.estore.products.Product;
import com.shoppingpro.estore.user.User;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public List<Category> getCategories(){
		return categoryService.getCategories();
	}
	
	@GetMapping("/products/{categoryId}")
	public List<Product> getUser(@PathVariable Integer categoryId) {
		return categoryService.getProducts(categoryId);
	}
	
}
