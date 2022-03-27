package com.shoppingpro.estore.user;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingpro.estore.exception.InvalidJwtException;
import com.shoppingpro.estore.security.JwtUtil;


@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found in db");
		}
		Collection<SimpleGrantedAuthority> authorities  =  new ArrayList<SimpleGrantedAuthority>();
		user.getRoles().forEach(role -> {
	
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		
		
	}
	
	public String refreshMyToken(String bearerToken, String url) throws InvalidJwtException {
		
		String username = jwtUtil.extractVerifiedTokenOwner(bearerToken);
		UserDetails userDetails = this.loadUserByUsername(username);
		
		String newAccessToken = jwtUtil.createAccessToken(userDetails, url);
		return newAccessToken;
	}
	
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	public void save(User user) {
		
		String EncodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(EncodedPassword);
		userRepository.save(user);
	}
	
	public User update(Integer id, Map<String, Object> updatedParams) throws UsernameNotFoundException {
		User user = userRepository.findById(id).orElseThrow(() -> 
			 new UsernameNotFoundException("user selected to be updated not found")
		);

		updatedParams.forEach((key, value)->{
			
			Field field = ReflectionUtils.findRequiredField(User.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, user, value);
		});
		userRepository.save(user);
		return user;
	}
	
	
	public void delete(int id) {
		userRepository.deleteById(id);	
	}
	
	public void update(User user) {
		userRepository.save(user);	
	}


	
}
