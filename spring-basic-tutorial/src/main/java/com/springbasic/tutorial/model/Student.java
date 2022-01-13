package com.springbasic.tutorial.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Student {

	int marks = 20;
	String name = "student1";
	
	Student() {
		System.out.println("student default name::"+name+" marks::"+marks);
	}
	public Student(String name, int marks) {
		System.out.println("student parameterized created name::"+name+" marks::"+marks);
		this.name = name;
		this.marks = marks;
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public void show() {
		System.out.println("student show name::"+name+" marks::"+marks);
	}
}
