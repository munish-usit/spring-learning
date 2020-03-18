package com.ihs.utils;

import java.io.File;


import org.apache.logging.log4j.Logger;

import com.ihs.constant.Configuration;


public class DiskManager {
	
	private static final Logger logger = AppLogger.getMainLogInstance(DiskManager.class);
	
	private static class DiskManagerHelper {
		private static final DiskManager instance = new DiskManager();
	}
	private String baseDirectory;
	
	private DiskManager() {
	}

	public static DiskManager getInstance() {
		return DiskManagerHelper.instance;
	}
	
	public  synchronized void configure(String baseDir)  {
		File f = new File(baseDir);
		if (!f.exists() || !f.isDirectory()) {
			logger.error("base directory " + baseDir + " not accessible!");
			return;
		}
		baseDirectory = baseDir;
	}
	
	public synchronized void configure()  {
		String baseDir = Configuration.getInstance().getConfig().get("baseDirPath").asText();
		configure(baseDir);
	}

	public String getAbsolutePath(String path) {
		return getAbsolutePath(baseDirectory, path);
	}
	
		
	private String getAbsolutePath(String directory, String filepath) {
		if (directory.endsWith(File.separator)) {
			return directory + filepath;
		}
		return directory + File.separator + filepath;
	}
	
	

}
