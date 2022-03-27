package com.shoppingpro.estore.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingpro.estore.products.Product;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getCategories() {
		return (List<Category>) categoryRepository.findAll();
	}
	
	public List<Product> getProducts(Integer categoryId){
		return categoryRepository.findById(categoryId).get().getProducts();
	}

}
