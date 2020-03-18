package com.ihs.junit.testcases;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ihs.constant.Configuration;

public class ConfigLoadTest {

	@Test
    public void loadDefaultConfig() {
		Configuration.getInstance().configure();
        String csvSplit = Configuration.getInstance().getConfig().get("csvsplit").asText();
        assertEquals(",",csvSplit);
    }
	
	@Test
    public void loadConfig() {
        Configuration.getInstance().configure("D:\\Credence\\priceservice\\src\\main\\resources\\config.json");
        String csvSplit = Configuration.getInstance().getConfig().get("dateformat").asText();
        assertEquals("dd-MM-yyyy",csvSplit);
    }
}
