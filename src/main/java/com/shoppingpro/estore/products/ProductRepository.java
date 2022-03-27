package com.shoppingpro.estore.products;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;


@Component
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	@Query(value="SELECT * FROM product WHERE name LIKE %:query%", nativeQuery=true)
	List<Product> search(@Param("query") String query);
}
