package com.springcloud.tutorial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.tutorial.client.DepartmentClient;
import com.springcloud.tutorial.entity.User;
import com.springcloud.tutorial.model.Department;
import com.springcloud.tutorial.model.ResponseDTO;
import com.springcloud.tutorial.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentClient departmentClient;
	
	public ResponseDTO getUser(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new RuntimeException("User Id Not Found");
		}
		User user = userOptional.get();
		Department department = departmentClient.getDepartment(user.getDepartmentId());
		log.info("department {}",department);
		return ResponseDTO.builder().user(user).department(department).build();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
