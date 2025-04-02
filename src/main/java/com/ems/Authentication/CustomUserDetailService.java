package com.ems.Authentication;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.Repository.UserRegisterRepo;
import com.ems.entity.User;
import com.ems.entity.UserRoles;

@Service
public class CustomUserDetailService implements UserDetailsService {
 
	@Autowired
	private final UserRegisterRepo userRepository;
	
	public CustomUserDetailService(UserRegisterRepo userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByUserName(userName);
		
		
		 if (userOptional.isEmpty()) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        User user = userOptional.get();
	        System.out.println(user.getUserName());
	        
	        java.util.Set<UserRoles> roles = user.getRole(); // Assuming 'role' is the field name

            // Print user roles to the console
            System.out.println("Logged-in User: " + user.getUserName());
            System.out.println("Roles: ");
            for (UserRoles role : roles) {
                System.out.println(role.getRoleName()); // Assuming UserRoles has a getName() method for the role name
            }
            String[] roleNames = roles.stream()
                    .map(role -> role.getRoleName())  // Assuming getName() returns the role name as a string
                    .toArray(String[]::new);
            
	        return  org.springframework.security.core.userdetails.User.builder()
                         .username(user.getUserName())
                         .password(user.getPassword())
                         .roles(roleNames)                       		 
                                 .build();         
	}

}
