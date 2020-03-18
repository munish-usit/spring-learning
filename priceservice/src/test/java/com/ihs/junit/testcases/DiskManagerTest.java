package com.ihs.junit.testcases;

import static org.junit.Assert.*;
import org.junit.Test;

import com.ihs.utils.DiskManager;


public class DiskManagerTest {

	@Test
    public void defaultBaseDir() {
		DiskManager.getInstance().configure();
        String absolutePath = DiskManager.getInstance().getAbsolutePath("newgen.csv");
        assertEquals("D:\\Credence\\priceservice\\src\\test\\resources\\data\\newgen.csv",absolutePath);
    }
	
	@Test
    public void configBaseDir() {
		DiskManager.getInstance().configure("D:\\data");
        String absolutePath = DiskManager.getInstance().getAbsolutePath("newgen.csv");
        assertEquals("D:\\data\\newgen.csv",absolutePath);
    }
}
