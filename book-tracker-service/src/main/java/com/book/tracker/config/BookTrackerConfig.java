package com.book.tracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class BookTrackerConfig {

	@Value("${book.tracker.dataload:false}")
	private boolean isDataLoad;

}
