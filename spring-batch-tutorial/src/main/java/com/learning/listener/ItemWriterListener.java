package com.learning.listener;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemWriterListener implements ItemWriteListener<String> {

	
	private Long rptId;
	
	private Long jobId;
	
	@Override
	public void onWriteError(Exception exception, Chunk<? extends String> items) {
		log.error("chunk on write error callback error for rptId {}, jobId {}",rptId,jobId);
		
		log.info("chunk on write error callback finished items size {}",items.size());
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.rptId  = stepExecution.getJobParameters().getLong("rptId");
		this.jobId = stepExecution.getJobExecution().getJobId();
		log.info("chunk on write error callback beforeStep  started for id {}, jobId {}",rptId,jobId);
	}
}
