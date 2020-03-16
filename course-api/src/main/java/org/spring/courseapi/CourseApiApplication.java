package org.spring.courseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication    // it is used to specify the main class.
public class CourseApiApplication {

	public static void main(String[] args) {
		// it create a servlet  in tomcat and host the application.
		// it also create a spring application context
		SpringApplication.run(CourseApiApplication.class, args);
	}

}
