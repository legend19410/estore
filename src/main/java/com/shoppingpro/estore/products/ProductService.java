package com.shoppingpro.estore.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> findAll(){
		return (List<Product>) productRepository.findAll();
	}
	
	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}
	
	public List<Product> search(String query){
		return productRepository.search(query);
	}
	
	

}
