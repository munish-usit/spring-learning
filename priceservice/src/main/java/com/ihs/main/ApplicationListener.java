package com.ihs.main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.Logger;
import com.ihs.constant.Configuration;
import com.ihs.utils.AppLogger;
import com.ihs.utils.DiskManager;


public class ApplicationListener implements ServletContextListener{

	private static final Logger logger = AppLogger.getMainLogInstance(ApplicationListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.debug("ApplicationListener started");
		Configuration.getInstance().configure();
		logger.debug("Config loaded");
		DiskManager.getInstance().configure();
		logger.debug("DiskManager configured");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
