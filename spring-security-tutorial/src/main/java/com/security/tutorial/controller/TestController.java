package com.security.tutorial.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.security.tutorial.model.LoginRequest;
import com.security.tutorial.model.LoginResponse;
import com.security.tutorial.service.LoginService;


@RestController
//@RequestMapping("/spring-security")

public class TestController {

	@Autowired
	private LoginService loginService;

	// accessible by admin only
	@GetMapping("/v1/admin")
	public String getAdminDetails() {
		System.out.println("Admin Details");
		return "Admin Details";
	}

	// accessible by admin and use
	@GetMapping("/v1/user")
	public String getUserDetails() {
		System.out.println("User Details");
		return "User Details";
	}

	// accept username and password, authenticate and return JWT token
	// Username and password can be passed as Request Body or Authorization Header
	@PostMapping("/v1/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
		System.out.println("Login request :: "+loginRequest);
		return loginService.login(loginRequest);
	}

	// accessible by all
	@GetMapping("/v1/ping")
	public String ping(@RequestHeader Map<String, String> headers) {
		System.out.println("Ping Details");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Header '%s' = %s", key, value));
		});
		return "pong";
	}

	// accessible by all
	@GetMapping("/v1/userinfo")
	public String userInfo(Principal principal) {
		return "Hi "+principal.getName()+" welcome to SpringCloudOauth2ExampleApplication";
	}
}
