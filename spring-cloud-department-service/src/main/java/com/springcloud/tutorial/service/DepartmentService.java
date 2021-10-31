package com.springcloud.tutorial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.tutorial.config.PropertyConfig;
import com.springcloud.tutorial.entity.Department;
import com.springcloud.tutorial.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private PropertyConfig propertyConfig;
	

	public Department getDepartment(Long departmentId) {
		Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
		if(!departmentOptional.isPresent()) {
			throw new RuntimeException("Department Id Not Found");
		}
		return departmentOptional.get();
	}

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	public PropertyConfig getProperty() {
		return PropertyConfig.builder()
				.developer(propertyConfig.getDeveloper())
				.message(propertyConfig.getMessage())
				.role(propertyConfig.getRole())
				.company(propertyConfig.getCompany())
				.serverPort(propertyConfig.getServerPort())
				.build();
	}
}
