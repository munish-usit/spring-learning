package com.springbasic.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.springbasic.tutorial.model.Student;
import com.springbasic.tutorial.service.MailService;
import com.springbasic.tutorial.service.StudentService;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = MailServiceAutoConfiguration.class)
public class SpringExamplesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringExamplesApplication.class, args);
		
		Student s1 = context.getBean("student1",Student.class);
		s1.show();
		
		s1.setMarks(40);
		
		Student s2 = context.getBean("student2",Student.class);
		s2.show();
		
		MailService mailService = context.getBean(MailService.class);
		mailService.sendMail();
		
		StudentService studentService = context.getBean(StudentService.class);
		studentService.show();
	}
	
	
	@Bean(name = "student1")
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Student getStudent1() {
		return new Student("student1",30);
	}
	
	@Bean(name = "student2")
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Student getStudent2() {
		return new Student("student2",60);
	}

}
