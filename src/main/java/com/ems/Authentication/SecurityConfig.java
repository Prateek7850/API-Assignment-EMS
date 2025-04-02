package com.ems.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ems.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	   @Autowired
       public CustomUserDetailService customUserDetailsService;
	
	   @Autowired
	   public JwtFilter jwtFilter;
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    	 AuthenticationManagerBuilder authenticationManagerBuilder = 
	 	            http.getSharedObject(AuthenticationManagerBuilder.class);
	 	        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
	 	            .passwordEncoder(passwordEncoder());
	 	        return authenticationManagerBuilder.build();
	    }
	    
	    @Bean 
	    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
	    	
	    	 http
	    	 .csrf()
	    	 .disable()
	            .authorizeHttpRequests()
	                .requestMatchers(HttpMethod.GET,"/auth/register").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
	                .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()// Public access for the register API
	                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
	                .anyRequest().permitAll()
	                .and()
	            .httpBasic()
	            .and()
	            .formLogin().permitAll()
	            .and()
	            .logout().permitAll();
	            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    	  // Basic Authentication
	        return http.build();
	    }
}
