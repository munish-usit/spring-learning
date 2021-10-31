package com.springcloud.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.tutorial.config.PropertyConfig;
import com.springcloud.tutorial.entity.Department;
import com.springcloud.tutorial.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private Environment environment;
	

	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		log.info("Inside saveDepartment method of DepartmentController");
		return  departmentService.saveDepartment(department);
	}

	@GetMapping("/{id}")
	public Department getDepartment(@PathVariable("id") Long departmentId) {
		String port = environment.getProperty("local.server.port");
		log.info("department port {}",port);
		log.info("Inside getDepartment method of DepartmentController");
		Department department = departmentService.getDepartment(departmentId);
		department.setDepartmentPort(port);
		log.info("department {}",department);
		return department;
	}
	
	@GetMapping("/property")
	public PropertyConfig getProperty() {
		return departmentService.getProperty();
	}
}
