package com.learning.config;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.learning.listener.CustomChunkListener;
import com.learning.listener.ItemWriterListener;
import com.learning.steps.MyTaskOne;
import com.learning.steps.MyTaskTwo;
import com.learning.steps.SimpleItemReader;
import com.learning.steps.SimpleItemWriter;

@Configuration
public class BatchConfiguration {

	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, Step stepOne, Step stepTwo) {
		return new JobBuilder("importUserJob", jobRepository).incrementer(new RunIdIncrementer())
				.flow(step1)
				//.next(stepOne)
				//.next(stepTwo)
				.end()
				.build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemWriter<String> writer) {
		return new StepBuilder("step1", jobRepository).<String, String>chunk(3, transactionManager)
				.reader(itemReader())
				.faultTolerant()
				.skipLimit(Integer.MAX_VALUE)
				.skip(Exception.class)
				//.skipPolicy(skipPolicy())
				//.listener(itemWriterListener())
				//.listener(new CustomChunkListener())
				.writer(getWriter())
				.build();
	}

	@Bean
	public Step stepOne(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOne", jobRepository).tasklet(new MyTaskOne(), transactionManager).build();
	}

	@Bean
	public Step stepTwo(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepTwo", jobRepository).tasklet(new MyTaskTwo(), transactionManager).build();
	}

	@Bean
	public ItemReader<String> itemReader() {
		return new SimpleItemReader();
	}

	@Bean
	public ItemWriter<String> getWriter() {
		return new SimpleItemWriter();
	}
	
	@Bean
	public SkipPolicy skipPolicy() {
		return (t,skipCount) -> true;
	}
	
	@Bean
	public ItemWriteListener<String> itemWriterListener() {
		return new ItemWriterListener();
	}
	

}
