package com.parkinglot.model;


public class Bill {

	
	private String billId;
	private String parkingTicket;
	private int billAmount;
	public Bill(String id, String ticket, int billAmount) {
		super();
		this.billId = id;
		this.parkingTicket = ticket;
		this.billAmount = billAmount;
	}
	public String getId() {
		return billId;
	}
	public void setId(String id) {
		this.billId = id;
	}
	public String getTicket() {
		return parkingTicket;
	}
	public void setTicket(String ticket) {
		this.parkingTicket = ticket;
	}
	public int getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}
	@Override
	public String toString() {
		return "Bill [id=" + billId + ", ticket=" + parkingTicket + ", billAmount=" + billAmount + "]";
	}
	
	
}
