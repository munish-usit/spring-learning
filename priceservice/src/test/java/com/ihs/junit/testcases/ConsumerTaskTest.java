package com.ihs.junit.testcases;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ihs.constant.Configuration;
import com.ihs.constant.ResponseStatus;
import com.ihs.db.GlobalMemory;
import com.ihs.utils.DiskManager;
import com.ihs.webservice.BatchService;
import com.ihs.webservice.LatestPrice;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

public class ConsumerTaskTest {

	@BeforeClass
	public static void setup() {
		Configuration.getInstance().configure();
		DiskManager.getInstance().configure();
	}

	@Before
	public void setupThis() {
	}

	@Test
	public void latestPriceAfterBatchCompleted() {
		BatchService pc = new BatchService();
		pc.startBatchTask(140, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LatestPrice lp = new LatestPrice();
		Response result = lp.getLatestPrice("INE619B01016");
		assertEquals(String.valueOf(190.0),result.getEntity().toString());

	}

	@Test
	public void latestPriceAfterMultipleBatchCompleted() {
		BatchService pc = new BatchService();
		pc.startBatchTask(141, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		BatchService pc2 = new BatchService();
		pc2.startBatchTask(142, DiskManager.getInstance().getAbsolutePath("newgen2.csv"));
		try {
			Thread.sleep(4*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LatestPrice lp = new LatestPrice();
		Response result = lp.getLatestPrice("INE619B01017");
		assertEquals(String.valueOf(256.0),result.getEntity().toString());

	}
			
	@Test
	public void latestPriceDuringBatchProgress() {
		BatchService pc = new BatchService();
		pc.startBatchTask(143, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		
		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BatchService pc2 = new BatchService();
		pc2.startBatchTask(144, DiskManager.getInstance().getAbsolutePath("newgen2.csv"));
		LatestPrice lp = new LatestPrice();
		Response result = lp.getLatestPrice("INE619B01017");
		assertEquals(String.valueOf(285.0),result.getEntity().toString());

	}
	
	@Test()
	public void latestPriceBeforeBatchStarted() {
		LatestPrice lp = new LatestPrice();
		Response result = lp.getLatestPrice("INE619B01019");
		BatchService pc = new BatchService();
		pc.startBatchTask(145, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		assertEquals(ResponseStatus.INSTRUMENT_NOT_FOUND.toString(),result.getEntity().toString());
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),result.getStatus());
	}
	
	@Test
	public void latestPriceAfterBatchCancelled() {
		BatchService pc = new BatchService();
		pc.startBatchTask(146, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		
		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BatchService pc2 = new BatchService();
		pc2.startBatchTask(147, DiskManager.getInstance().getAbsolutePath("newgen2.csv"));
		pc2.stopBatchTask(147);
		LatestPrice lp = new LatestPrice();
		Response result = lp.getLatestPrice("INE619B01016");
		assertEquals(String.valueOf(190.0),result.getEntity().toString());

	}


	@After
	public void tearThis() {
		GlobalMemory.getGlobalMemory().getInstrumentPriceMap().clear();
		GlobalMemory.getGlobalMemory().getTaskMap().clear();
	}

	@AfterClass
	public static void tear() {
	}

}
