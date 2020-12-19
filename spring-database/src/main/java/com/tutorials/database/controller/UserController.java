package com.tutorials.database.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutorials.database.model.User;
import com.tutorials.database.repository.UserRepository;


@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user);
	}


	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		User user = new User();
		user.setUserId(userId);
		Example<User> userExample = Example.of(user);
		Optional<User> userOptiona = userRepository.findOne(userExample);
		if (userOptiona.isPresent()) {
			return userOptiona.get().getUserSettings();
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get().getUserSettings().get(key);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.PUT)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			user.get().getUserSettings().put(key, value);
			userRepository.save(user.get());
			return "Key added";
		} else {
			return "User not found.";
		}
	}

	@GetMapping(value="/ping")
	public String ping() {
		return "pong";
	}

}
