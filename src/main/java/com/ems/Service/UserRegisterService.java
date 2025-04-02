package com.ems.Service;

import java.util.List;

import com.ems.entity.User;

public interface UserRegisterService {
       
	public void addUser(User user);

	public List<User> getAllUser();
	
    
}
