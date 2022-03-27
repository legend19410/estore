package com.shoppingpro.estore.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingpro.estore.user.User;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public List<Product> get() {
		return productService.findAll();
	}
	
	@GetMapping("/search")
	public List<Product> search( @RequestParam("query") String query){
		return productService.search(query); 
	}
	
	@GetMapping("/save")
	public String products() {
		
//		 {id: 1, name:"Banana", image:"dish-1.png", price:13.99},
//	        {id: 2, name:"Banana", image:"dish-2.png", price:13.99},
//	        {id: 3, name:"Banana", image:"dish-3.png", price:13.99},
//	        {id: 4, name:"Banana", image:"dish-4.png", price:13.99},
//	        {id: 5, name:"Banana", image:"dish-5.png", price:13.99},
//	        {id: 6, name:"Banana", image:"dish-6.png", price:13.99},
//	        {id: 7, name:"Banana", image:"dish-5.png", price:13.99},
//	        {id: 8, name:"Banana", image:"dish-5.png", price:13.99}
	        
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "Banana" , 13.99, "bunch", 34, "dish-1.png"));
		products.add(new Product(2, "Banana" , 13.99, "bunch", 34, "dish-2.png"));
		products.add(new Product(3, "Banana" , 13.99, "bunch", 34, "dish-3.png"));
		products.add(new Product(4, "Banana" , 13.99, "bunch", 34, "dish-4.png"));
		products.add(new Product(5, "Banana" , 13.99, "bunch", 34, "dish-5.png"));
		products.add(new Product(6, "Banana" , 13.99, "bunch", 34, "dish-6.png"));
		products.add(new Product(7, "Banana" , 13.99, "bunch", 34, "dish-6.png"));
		products.add(new Product(8, "Banana" , 13.99, "bunch", 34, "dish-6.png"));
		
//		productService.save(new Product(8, "Banana" , 13.99, "bunch", 34, "dish-6.png"));
		
		products.forEach((product)->{
			productService.save(product);
		});
		
		System.out.println(products);
//		
		return "check db";
	}
	

}
