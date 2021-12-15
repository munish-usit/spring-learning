package com.book.tracker.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.book.tracker.config.BookTrackerConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoaderApplication implements ApplicationRunner{

	@Autowired
	private BookTrackerConfig config;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		boolean dataLoad = config.isDataLoad();
		log.info("Application started with data load: {}", dataLoad);
		if(dataLoad) {

		}
	}

}
