package com.parkinglot.model;

import java.time.LocalDateTime;




public class ParkingDuration {

	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	
	public ParkingDuration() {
		
	}
	public ParkingDuration(LocalDateTime checkIn, LocalDateTime checkOut) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public LocalDateTime getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDateTime checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDateTime getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDateTime checkOut) {
		this.checkOut = checkOut;
	}
	
	
}
