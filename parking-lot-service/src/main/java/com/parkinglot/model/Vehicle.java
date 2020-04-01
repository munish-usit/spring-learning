package com.parkinglot.model;

public class Vehicle {

	private int vehicleno;
	private String name;
	
	public Vehicle() {
		
	}
	public Vehicle(int vehicleno, String name) {
		super();
		this.vehicleno = vehicleno;
		this.name = name;
	}
	public int getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(int vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Vehicle [vehicleno=" + vehicleno + ", name=" + name + "]";
	}
	
	
	
	
	
}
