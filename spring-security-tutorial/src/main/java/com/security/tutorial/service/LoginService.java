package com.security.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.security.tutorial.model.LoginRequest;
import com.security.tutorial.model.LoginResponse;
import com.security.tutorial.util.JwtUtil;

@Service
public class LoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) throws Exception {
		
		// Authenticate the user using spring security AuthenticationManager
		UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		try {
			authenticationManager.authenticate(usernamePasswordToken);
		} catch (BadCredentialsException e) {
			System.out.println("incorrect username and password");
			throw new Exception("Incorrect Username and Password",e);
		}
		
		// After successful authentication, create jwt token
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println("jwtToken :: "+jwtToken);
		return ResponseEntity.ok(new LoginResponse(jwtToken));
	}
}
