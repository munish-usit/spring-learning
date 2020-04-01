
package com.parkinglot.model;

public class UserInfo {

	private String name;
	private String address;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", address=" + address + "]";
	}
	
	
}
