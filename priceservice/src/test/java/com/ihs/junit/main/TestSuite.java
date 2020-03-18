package com.ihs.junit.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ihs.junit.testcases.BatchTaskProducerTest;
import com.ihs.junit.testcases.ConfigLoadTest;
import com.ihs.junit.testcases.ConsumerTaskTest;
import com.ihs.junit.testcases.DiskManagerTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   ConfigLoadTest.class,
   DiskManagerTest.class,
   BatchTaskProducerTest.class,
   ConsumerTaskTest.class
})
public class TestSuite {

}
