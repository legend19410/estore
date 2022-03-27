package com.shoppingpro.estore.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query(value="SELECT * FROM user WHERE email=:username", nativeQuery=true)
	User findByUsername( String username);
	
}
