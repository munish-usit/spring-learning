package com.springcloud.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.tutorial.entity.User;
import com.springcloud.tutorial.model.ResponseDTO;
import com.springcloud.tutorial.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		log.info("Inside saveUser method of UserController");
		return  userService.saveUser(user);
	}

	@GetMapping("/{id}")
	public ResponseDTO getUser(@PathVariable("id") Long userId) {
		log.info("Inside getUser method of UserController");
		ResponseDTO response = userService.getUser(userId);
		log.info("response {}",response);
		return response;
	}
}
