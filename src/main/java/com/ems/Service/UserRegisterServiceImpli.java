package com.ems.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.Repository.UserRegisterRepo;
import com.ems.entity.User;

@Service
public class UserRegisterServiceImpli implements UserRegisterService {

	@Autowired
	private UserRegisterRepo userRegisterRepo;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public void addUser(User user) {
		String password = user.getPassword();
		   user.setPassword(passwordEncoder.encode(password));
		userRegisterRepo.save(user);
	}
	@Override
	public List<User> getAllUser() {
		List<User> user = userRegisterRepo.findAll();
		return user;
	}

}
