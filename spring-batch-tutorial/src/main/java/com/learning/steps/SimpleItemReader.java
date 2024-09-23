package com.learning.steps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SimpleItemReader implements ItemReader<String> {

	private Iterator<String> iterator;
	
	private  Long rptId;

	public SimpleItemReader() {
		System.out.println("reader constructor");
		
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return iterator.hasNext() ? iterator.next() : null;
	}
	
	@BeforeRead
	public void beforeRead() {
		//System.out.println("reader before read :: "+rptId);
		
		//List<String> dataSet = dataSet();
		//this.iterator = dataSet.iterator();
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		//rptId = stepExecution.getJobParameters().getLong("rptId");
		//System.out.println("reader before steps :: "+rptId);
		
		List<String> dataSet = dataSet();
		this.iterator = dataSet.iterator();
	}
	
	private List<String> dataSet() {
		System.out.println("get items for report "+rptId);
		List<String> dataSet = new ArrayList<>();
		dataSet.add("1");
		dataSet.add("2");
		dataSet.add("3");
		dataSet.add("4");
		dataSet.add("5");
		return dataSet;
	}

}
