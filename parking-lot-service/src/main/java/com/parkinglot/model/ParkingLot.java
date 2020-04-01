package com.parkinglot.model;


public class ParkingLot {

	private int lotId;
		
	public ParkingLot() {
		
	}
	
	public ParkingLot(int id) {
		super();
		this.lotId = id;
		
	}

	public int getId() {
		return lotId;
	}

	public void setId(int id) {
		this.lotId = id;
	}

	@Override
	public String toString() {
		return "ParkingLot [id=" + lotId + "]";
	}
	
	
	
	
}
