package com.tutorials.database.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Article {
	@Id
	@Column(name="article_id")
	private String id;
	@Column(name="article_name")
	private String name;
	
	@ManyToMany
	private Collection<UserDetails> users;
	
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

	public Collection<UserDetails> getUsers() {
		return users;
	}
	public void setUsers(Collection<UserDetails> users) {
		this.users = users;
	}

	
}
