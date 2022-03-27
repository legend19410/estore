package com.shoppingpro.estore.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingpro.estore.exception.InvalidJwtException;

import com.shoppingpro.estore.security.AuthenticationResponse;
import com.shoppingpro.estore.security.DecodedToken;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> users() {
		return userService.findAll();
	}
	
	@GetMapping("/user")
	public User currentUser(@RequestHeader(name="Authorization") String encodedtoken) throws UnsupportedEncodingException {
		DecodedToken decodedToken = DecodedToken.getDecoded(encodedtoken);
		String username = decodedToken.sub;
		return userService.findByUsername(username);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	@PostMapping("/save")
	public User save(@Valid @RequestBody User user){
		userService.save(user);
		return user;
	}
	

	@PatchMapping("/{id}")
	public User update(@PathVariable Integer id, @RequestBody Map<String, Object> updatedParams) throws UsernameNotFoundException{
		return userService.update(id, updatedParams);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id){
		userService.delete(id);
		return "user deleted"; 
	}
	
	@GetMapping("/refresh-token")
	public AuthenticationResponse refreshMyToken(HttpServletRequest request, HttpServletResponse response) throws InvalidJwtException{
		String bearerToken = request.getHeader("Authorization");
		String url = request.getRequestURL().toString();
		String newAccessToken = userService.refreshMyToken(bearerToken, url);
		String refreshToken = bearerToken.substring("Bearer ".length());
		return new AuthenticationResponse(newAccessToken, refreshToken);
	}
	

}
