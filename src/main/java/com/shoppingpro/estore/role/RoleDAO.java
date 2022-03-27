package com.shoppingpro.estore.role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface RoleDAO extends CrudRepository<Role, Integer>{

	@Query(value="SELECT * FROM role WHERE type=:name", nativeQuery=true)
	Role getRoleByRoleName( String name);
	
}
