/**
 * Topic is treated as model by Spring MVC.
 * Spring MVC internally uses jackson json library to convert this into json representation.It is similar to Jersey framework
 * public no-arg constructor is mandatory
 * Getter methods are mandatory
 */
package org.spring.courseapi.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {
	
	@Id
	private int id;
	private String name;
	private String description;
	
	public Topic() {
		System.out.println("topic constructor");
	}
	public Topic(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	/*
	 * public void setId(int id) { this.id = id; } public void setDescription(String
	 * description) { this.description = description; } public void setName(String
	 * name) { this.name = name; }
	 */
	

}
