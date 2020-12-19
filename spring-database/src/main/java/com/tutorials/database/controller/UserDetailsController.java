package com.tutorials.database.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutorials.database.model.UserDetails;
import com.tutorials.database.repository.UserDetailsRepository;


@RestController
@RequestMapping(value = "/usersdetails")
public class UserDetailsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<UserDetails> getAllUsers() {
		LOG.info("Getting all users.");
		return (List<UserDetails>) userDetailsRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> getUser(@PathVariable Long userId) {
		LOG.info("Getting user with ID: {}.", userId);
		Optional<UserDetails> user = userDetailsRepository.findById(userId);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public UserDetails addNewUsers(@RequestBody UserDetails user) {
		LOG.info("Saving user.");
		return userDetailsRepository.save(user);
	}




	@GetMapping(value="/ping")
	public String ping() {
		return "pong";
	}

}
