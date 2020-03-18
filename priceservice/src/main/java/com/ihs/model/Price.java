package com.ihs.model;

public class Price {

	private double price;
	public Price(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.valueOf(price);
		
	}
}
