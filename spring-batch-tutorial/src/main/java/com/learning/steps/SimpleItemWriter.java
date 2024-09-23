package com.learning.steps;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SimpleItemWriter implements ItemWriter<String>{

	private Long rptId;
	
	public SimpleItemWriter() {
		System.out.println("writer constructor");
	}
	@Override
	public void write(Chunk<? extends String> chunk) throws Exception {
		System.out.println("chunk size ::"+chunk.size());
		List<String> tempItems = (List<String>) chunk.getItems();
		System.out.println("temp items size ::"+chunk.size());
		System.out.println("temp items ::"+tempItems);
	}
	
	@BeforeWrite
	public void beforeWrite() {
		//System.out.println("writer before write :: "+rptId);
	}
	
	@AfterWrite
	public void afterWrite() {
		//System.out.println("writer after write :: "+rptId);
	}
	
	@OnWriteError
	public void onWriteError(Exception exception, Chunk<? extends String> items) {
		//System.out.println("chunk on write error callback error for rptId "+rptId);
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		//rptId = stepExecution.getJobParameters().getLong("rptId");
		//System.out.println("writer before steps :: "+rptId);
	}



}
