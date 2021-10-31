/**
 * Creating Rest Controller in Spring Boot is similar to creating Rest Web Service using Jersey JAX-RS framework.
 * Both spring boot and Jersey use annotations to create rest webservices.
 * Spring boot internally uses Spring MVC framework.
 * @RestContoller -- specify that this class is rest controller and methods are mapped to some url
 * @RequestMapping - specify request url and type and params
 */
package org.spring.courseapi.topic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Munish";
	}
	
}
