package com.ihs.junit.main;

import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.ihs.utils.AppLogger;

public class TestRunner {
	
	private static final Logger logger = AppLogger.getMainLogInstance(TestRunner.class);
	public static void main(String [] args) {
		Result result = JUnitCore.runClasses(TestSuite.class);
		
		for(Failure failure : result.getFailures()) {
			logger.error("test case failed with reason :: {} ",failure.toString());
		}
		logger.info("test cases passed :: {}",result.wasSuccessful());
		logger.info("test cases time {}",result.getRunTime());
	}

}
