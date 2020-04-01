package com.parkinglot.constant;

public enum WeekendRate {
	
	TWO_HOURS(5),
	FIVE_HOURS(8),
	TEN_HOURS(12),
	FIFTEEN_HOURS(18),
	TWENTYFOUR_HOURS(25);
	
	
	private int rate;

	WeekendRate(int rate) {
		this.rate = rate;
	}
	
	public int getRate() {
		return this.rate;
	}
	
	
}
