package com.ems.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Authentication.CustomUserDetailService;
import com.ems.Service.UserRegisterService;
import com.ems.entity.User;
import com.ems.utill.JwtUtil;

@RestController
@RequestMapping("/auth")
public class RegistrationController {
          
	@Autowired
	private UserRegisterService userRegisterService;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		userRegisterService.addUser(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@GetMapping("/register")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> user = userRegisterService.getAllUser();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}     
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user){
		try {
		authenticationManager.authenticate
		(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		UserDetails userDetails = customUserDetailService.loadUserByUsername(user.getUserName());
		          String jwt = jwtUtil.generateToken(userDetails.getUsername());
		return new ResponseEntity(jwt,HttpStatus.OK);
		} catch(Exception e) {
		         return new ResponseEntity("User and Password is wrong!",HttpStatus.UNAUTHORIZED);
		}
	
	}
}
