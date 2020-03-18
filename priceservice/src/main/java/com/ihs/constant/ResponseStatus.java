package com.ihs.constant;

public enum ResponseStatus {
	
	BATCH_START_SUCCESSFULL("batch successfully started"),
	BATCH_START_FAILED("exception in starting batch"),
	BATCH_STOP_SUCCESSFULL("batch successfully cancelled"),
	BATCH_STOP_FAILED("exception in cancelling batch"),
	DUPLICATE_BATCH_ID("duplicate batch id"),
	BATCH_NOT_FOUND("batch id doesn't exist"),
	TASK_ALREADY_COMPLETED("batch task already completed"),
	INSTRUMENT_NOT_FOUND("instrument id doesn't exist"),
	LATESTPRICE_FAILED("exception in fetching latest price");
	
	private String message;

	ResponseStatus(String message) {
		this.message = message;
	}
	
	public String toString() {
		return this.message;
	}
}
