package com.parkinglot.utils;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.JsonNode;


public final class Configuration {
	
	private static final Logger logger = AppLogger.getMainLogInstance(Configuration.class);
	private static Configuration instance = new Configuration();
	private JsonNode configNode;
	
	private Configuration() {
		
	}
	public static Configuration getInstance() {
		return instance;
	}
	public void configure(String configPath)  {
		JsonNode all = null;
		try {
			all = JacksonHelper.getMapper().readTree(new File(configPath));
			this.configNode = all.get("common");
		} catch (IOException e) {
			logger.error("config file not loaded properly {} ",e);
			
		}
		
	}
	public void configure() {
		File file = new File(getClass().getClassLoader().getResource("config.json").getFile());
		JsonNode all = null;
		try {
			all = JacksonHelper.getMapper().readTree(file);
			this.configNode = all.get("common");
		} catch (IOException e) {
			logger.error("default config file not loaded properly {} ",e);
			
		}
	}
	
	public JsonNode getConfig() {
		return configNode;
	}
	
}
