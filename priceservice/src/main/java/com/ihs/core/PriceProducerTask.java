package com.ihs.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.ihs.constant.BatchStatus;
import com.ihs.db.GlobalMemory;
import com.ihs.constant.Configuration;
import com.ihs.model.Instrument;
import com.ihs.model.Price;
import com.ihs.utils.AppLogger;
import com.ihs.utils.DiskManager;


public class PriceProducerTask implements Runnable {

	private static final Logger logger = AppLogger.getMainLogInstance(PriceProducerTask.class);
	private int id;
	private String filePath;
	private BatchStatus status;
	private Map<String,Instrument> localInstrumentPriceMap;

	public PriceProducerTask(int id,String filePath) {
		this.id = id;
		this.filePath = correctFilePath(filePath);
		this.status = BatchStatus.STARTED;
		localInstrumentPriceMap = new HashMap<String,Instrument>();
	}
	
	public Map<String,Instrument> getLocalInstrumentPriceMap() {
		return localInstrumentPriceMap;
	}
	
	public BatchStatus getStatus() {
		return status;
	}
	public void setStatus(BatchStatus status) {
		this.status = status;
	}
	
	private String correctFilePath(String filePath) {
		File f = new File(filePath);
		if(f.exists() && f.isFile()) return filePath;
		else return DiskManager.getInstance().getAbsolutePath(filePath);
	}

	public void run() {
		status = BatchStatus.INPROGRESS;
		logger.info("batch {} :: is in progress, reading file {} ",id,filePath);
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line = "";
			while ((line = reader.readLine()) != null) {
				if(status.equals(BatchStatus.CANCELLED)) {
					localInstrumentPriceMap.clear();
					return;
				}
				processData(line.trim());
			}
			updateGlobalInstrumentPrice();
			status = BatchStatus.COMPLETED;
			logger.info("batch {} :: is completed",id);

		} catch (IOException e) {
			logger.error("batch file not loaded properly {} ",e);
		}
	}

	public void cancel() {
		logger.info("batch {} :: is cancelled",id);
		setStatus(BatchStatus.CANCELLED);
	}
	private  void processData(String line) {
		logger.trace("batch {} :: processing line {} ",id,line);
		String [] data = line.split(Configuration.getInstance().getConfig().get("csvsplit").asText());
		if(data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty()) {
			logger.warn("batch {} :: line {} not valid",id,line);
			return;
		}
		Date date;
		try {
			date = new SimpleDateFormat(Configuration.getInstance().getConfig().get("dateformat").asText()).parse(data[1].trim());
		} catch (ParseException e) {
			logger.error("batch {} :: date {} not valid throwing exception {}",id,data[1],e);
			return;
		}
		String isin = data[0].trim();
		Double price = Double.valueOf(data[2].trim());
		Instrument instrument = new Instrument(isin,date,new Price(price));
		if(localInstrumentPriceMap.containsKey(isin)) {
			Date existingDate = localInstrumentPriceMap.get(isin).getDate();
			if(date.compareTo(existingDate) > 0) {
				localInstrumentPriceMap.put(isin, instrument);
			}
		} else {
			localInstrumentPriceMap.put(isin, instrument);
		}
		
	}
	
	private void updateGlobalInstrumentPrice() {
		logger.trace("batch {} :: localInstrumentPrice {} ",id,localInstrumentPriceMap.toString());
		if(localInstrumentPriceMap == null || localInstrumentPriceMap.isEmpty()) {
			logger.info("batch {} :: localInstrumentPrice null ",id);
			return;
		}
		GlobalMemory.getGlobalMemory().updateGlobalInstrumentPrice(localInstrumentPriceMap);
		
	}
	
	
	



}
