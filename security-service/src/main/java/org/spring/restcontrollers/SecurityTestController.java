/**
 * Rating  Service return rating information (movie model) for a particular movie
 * Rating model is used to represent rating info for a particular movie id
 * Eg Movie id 12 has rating 4
 */
package org.spring.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

	@RequestMapping("/welcome/{name}")
    public String welcomeMessage(@PathVariable("name") String name) {
        return "Welcome "+name.toUpperCase();
    }
	
	@RequestMapping("/hello/{name}")
    public String helloMessage(@PathVariable("name") String name) {
        return "Hello "+name.toUpperCase();
    }
	
	@GetMapping("/greetings/{username}")
	public String getGreetings(@PathVariable("username") String userName) {
	    return "Hello " + userName + ", Good day...!!!";
	}
	
	// USER ROLE
	@RequestMapping("/user/{name}")
    public String userName(@PathVariable("name") String name) {
        return "Hello USER "+name.toUpperCase();
    }
	
	// ADMIN ROLE
	@RequestMapping("/admin/{name}")
    public String adminName(@PathVariable("name") String name) {
        return "Hello ADMIN "+name.toUpperCase();
    }
	
	

	
	
	 
}
