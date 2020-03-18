package com.ihs.model;

import java.util.Date;

public class Instrument {
	
	private String isin;
	private Date date;
	private Price price;
		
	public Instrument(String isin,Date date,Price price) {
		this.isin = isin;
		this.date = date;
		this.price = price;
	}

	public String getIsin() {
		return isin;
	}

	public Date getDate() {
		return date;
	}

	public Price getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return isin + "|"+date.toString()+"|"+price.toString();
		
	}
	

}
