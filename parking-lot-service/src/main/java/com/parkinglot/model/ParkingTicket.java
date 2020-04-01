package com.parkinglot.model;

public class ParkingTicket {
	

	private String ticketId;
	private ParkingLot lot;
	private Vehicle vehicle;
	private UserInfo userinfo;
	private ParkingDuration duration;
	
	ParkingTicket() {
		
	}
	public ParkingTicket(String id, ParkingLot lot, Vehicle vehicle, UserInfo userinfo, ParkingDuration duration) {
		super();
		this.ticketId = id;
		this.lot = lot;
		this.vehicle = vehicle;
		this.userinfo = userinfo;
		this.duration = duration;
	}
	public String getId() {
		return ticketId;
	}
	public void setId(String id) {
		this.ticketId = id;
	}
	public ParkingLot getLot() {
		return lot;
	}
	public void setLot(ParkingLot lot) {
		this.lot = lot;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public ParkingDuration getDuration() {
		return duration;
	}
	public void setDuration(ParkingDuration duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "ParkingTicket [id=" + ticketId + ", lot=" + lot + ", vehicle=" + vehicle + ", userinfo=" + userinfo
				+ ", duration=" + duration + "]";
	}
	
	
	
	
}
