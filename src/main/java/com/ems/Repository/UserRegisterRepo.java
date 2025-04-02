package com.ems.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.User;

public interface UserRegisterRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);
             
	 
}
