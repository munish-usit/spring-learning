package com.springbasic.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springbasic.tutorial.model.Student;

@Service
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentService {
	
	@Autowired
	@Qualifier("student1")
	Student student;
	
	StudentService() {
		System.out.println("student service constructor");
	}
	
	public void show() {
		student.show();
	}
}
