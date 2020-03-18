package com.ihs.db;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.Logger;
import com.ihs.model.Instrument;
import com.ihs.utils.AppLogger;

public class GlobalMemory {

	private static final Logger logger = AppLogger.getMainLogInstance(GlobalMemory.class);
	private Map<String,Instrument> instrumentPriceMap;
	private Map<Integer,Runnable> taskMap;
	private GlobalMemory() {
		instrumentPriceMap = new ConcurrentHashMap<String,Instrument>();
		taskMap = new HashMap<Integer,Runnable>();
	}
	
	private static class GlobalMemoryHelper {
		private static final GlobalMemory instance = new GlobalMemory();
	}
	public static GlobalMemory getGlobalMemory() {
		return GlobalMemoryHelper.instance;
	}

	public Map<String, Instrument> getInstrumentPriceMap() {
		return instrumentPriceMap;
	}
	public void setInstrumentPriceMap(Map<String, Instrument> instrumentPriceMap) {
		this.instrumentPriceMap = instrumentPriceMap;
	}

	public Map<Integer, Runnable> getTaskMap() {
		return taskMap;
	}
	public void setTaskMap(Map<Integer, Runnable> taskMap) {
		this.taskMap = taskMap;
	}
	public synchronized void updateGlobalInstrumentPrice(Map<String,Instrument> localInstrumentPriceMap) {
		try {
			for(String isin : localInstrumentPriceMap.keySet() ) {
				Instrument instrument = localInstrumentPriceMap.get(isin);
				if(instrumentPriceMap.containsKey(isin)) {
					Date localDate = localInstrumentPriceMap.get(isin).getDate();
					Date globalDate = instrumentPriceMap.get(isin).getDate();
					if(localDate.compareTo(globalDate) > 0) {
						instrumentPriceMap.put(isin, instrument);
					}
				} else {
					instrumentPriceMap.put(isin, instrument);
				}
			}
			logger.debug("updateGlobalInstrumentPrice {} ",instrumentPriceMap.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("error in updating globalInstrumentPriceMap {} ",e);
		}
	}


}
