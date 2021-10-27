package com.security.tutorial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.tutorial.entity.UserEntity;
import com.security.tutorial.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	/**
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Hardcoded user
		System.out.println("username :: "+username);
		if(username == null) {
			System.out.println("username null throwing exception");
			throw new UsernameNotFoundException(username);
		}
		String password = "user";
		String roles = "USER";
		if(username.equalsIgnoreCase("admin")) {
			password = "admin";
			roles = "ADMIN";
		}
		UserDetails user =  User.withUsername(username).password(password).roles(roles).build();
		System.out.println("user details :: "+user);
		return user;
	}
	**/ 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetching data using repository and using Spring UserBuilder to create UserDetails object
		System.out.println("username :: "+username);
		Optional<UserEntity> userEntityOptional = userRepository.findById(username);
		if(!userEntityOptional.isPresent()) {
			System.out.println("user not found..throwing exception");
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntityOptional.get();
		System.out.println("userEntity :: "+userEntity);
		UserDetails user =  User.withUsername(username)
				.password(userEntity.getPassword())
				.roles(userEntity.getRoles()).disabled(!userEntity.isEnabled())
				.build();
		System.out.println("user details :: "+user);
		return user;
	}

}
