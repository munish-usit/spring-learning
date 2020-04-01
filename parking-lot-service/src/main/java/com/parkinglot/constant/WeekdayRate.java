package com.parkinglot.constant;

public enum WeekdayRate {
	
	TWO_HOURS(7),
	FIVE_HOURS(10),
	TEN_HOURS(15),
	FIFTEEN_HOURS(22),
	TWENTYFOUR_HOURS(30);
		
	private int rate;

	WeekdayRate(int charges) {
		this.rate = charges;
	}
	
	public int getRate() {
		return this.rate;
	}
	
	
	
	
}
