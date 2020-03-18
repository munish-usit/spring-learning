package com.ihs.junit.testcases;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ihs.constant.Configuration;
import com.ihs.constant.ResponseStatus;
import com.ihs.core.PriceProducerTask;
import com.ihs.db.GlobalMemory;
import com.ihs.utils.DiskManager;
import com.ihs.webservice.BatchService;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;


public class BatchTaskProducerTest {

	@BeforeClass
	public static void setup() {
		Configuration.getInstance().configure();
		DiskManager.getInstance().configure();
	}

	@Before
	public void setupThis() {
	}

	@Test
	public void startBatchTaskWithId() {
		BatchService pc = new BatchService();
		Response result = pc.startBatchTask(123, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		assertEquals(Response.Status.OK.getStatusCode(),result.getStatus());

	}
	
	@Test
	public void startBatchTaskWithSameId() {
		BatchService pc = new BatchService();
		pc.startBatchTask(124, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		Response result = pc.startBatchTask(124, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		assertEquals(ResponseStatus.DUPLICATE_BATCH_ID.toString(),result.getEntity().toString());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),result.getStatus());

	}

	@Test
	@Ignore
	public void cancelBatchTaskIdExist() {
		BatchService pc = new BatchService();
		pc.startBatchTask(125, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		pc.stopBatchTask(125);
		PriceProducerTask task = (PriceProducerTask) GlobalMemory.getGlobalMemory().getTaskMap().get(125);
		assertTrue(task.getLocalInstrumentPriceMap().isEmpty());

	}
	
	@Test
	public void cancelBatchTaskIdNotExist() {
		BatchService pc = new BatchService();
		pc.startBatchTask(126, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		Response result = pc.stopBatchTask(127);
		assertEquals(ResponseStatus.BATCH_NOT_FOUND.toString(),result.getEntity().toString());
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),result.getStatus());
	}
	
	@Test
	public void cancelBatchTaskIdCompleted() {
		BatchService pc = new BatchService();
		pc.startBatchTask(128, DiskManager.getInstance().getAbsolutePath("newgen.csv"));
		try {
			Thread.sleep(4*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response result = pc.stopBatchTask(128);
		assertEquals(ResponseStatus.TASK_ALREADY_COMPLETED.toString(),result.getEntity().toString());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),result.getStatus());

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
