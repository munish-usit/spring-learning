package com.tutorials.database.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InterestAreas {

	@Column(name="interest_id")
	private String id;
	@Column(name="interest_name")
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
