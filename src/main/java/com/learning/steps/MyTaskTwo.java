package com.learning.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.learning.ActiveFileInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTaskTwo implements Tasklet {

	public MyTaskTwo() {
		System.out.println("my task two constructor");
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("MyTaskTwo start..");

		// ... your code

		log.info("task two chunk context {}", chunkContext);
		ActiveFileInfo active = (ActiveFileInfo) chunkContext.getAttribute("active");
		log.info("task two active {}", active);

		System.out.println("MyTaskTwo done..");
		return RepeatStatus.FINISHED;
	}
}
