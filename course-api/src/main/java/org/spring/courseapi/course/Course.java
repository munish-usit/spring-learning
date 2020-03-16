
package org.spring.courseapi.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Course {
	
	@Id
	private int id;
	private String name;
	private String description;
	
	///@ManyToOne
	private int topicid; // a course will be mapped to a particular topic. Course -> topicid is the foreign key and refers to Topic -> id primary key. A topic will have many course
	
	public Course() {
		System.out.println("course constructor");
	}
	public Course(int id, String name, String description,int topicid) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.topicid = topicid;
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
	public int getTopicid() {
		return topicid;
	}
	
	public void setTopicid(int topicId) {
		topicid = topicId;
	}
	
	/*
	 * public void setId(int id) { this.id = id; } public void setDescription(String
	 * description) { this.description = description; } public void setName(String
	 * name) { this.name = name; }
	 */
	

}
