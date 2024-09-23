package com.learning.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.learning.ActiveFileInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTaskOne implements Tasklet {

	public MyTaskOne() {
		System.out.println("my task one constructor");
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("MyTaskOne start..");

		// ... your code
		ActiveFileInfo active = new ActiveFileInfo();
		active.setId("10");
		active.setName("report-object");
		
		chunkContext.setAttribute("active", active);
		
		Long rptId = contribution.getStepExecution().getJobParameters().getLong("rptId");
		Object rptIdString =  chunkContext.getStepContext().getJobParameters().get("rptId");
		
		log.info("task one chunk context {} {}", rptId,String.valueOf(rptIdString));

		System.out.println("MyTaskOne done..");
		return RepeatStatus.FINISHED;
	}
}