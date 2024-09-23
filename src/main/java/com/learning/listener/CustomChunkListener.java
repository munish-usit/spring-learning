package com.learning.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class CustomChunkListener implements ChunkListener {

    @Override
	public void afterChunkError(ChunkContext context) {
    	Long rptId = context.getStepContext().getStepExecution().getJobParameters().getLong("rptId");
		System.out.println("Called afterChunkError() rptId "+rptId);
	}
}