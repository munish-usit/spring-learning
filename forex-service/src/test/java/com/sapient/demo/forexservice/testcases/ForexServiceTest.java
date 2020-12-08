package com.sapient.demo.forexservice.testcases;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.demo.forexservice.models.ForexModel;
import com.sapient.demo.forexservice.repositories.ForexRepository;
import com.sapient.demo.forexservice.service.ForexService;

@RunWith(SpringRunner.class)
public class ForexServiceTest {

	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
 
        @Bean
        public ForexService forexService() {
            return new ForexService();
        }
    }
	
	@Autowired
	private ForexService forexService;
	
	@MockBean
	private ForexRepository forexRepo;
	
	@Before
	public void setUp() {
		ForexModel model = new ForexModel(1L, "DEN", "INR", BigDecimal.valueOf(50));
	    Mockito.when(forexRepo.findByFromAndTo("DEN", "INR")).thenReturn(model);
	}
	
	@Test
	public void forexServiceFindByFromAndTo() {
		String value = "50";
	    ForexModel found = forexService.findByFromAndTo("DEN", "INR");
	    assertEquals(String.valueOf(found.getConversionMultiple()),value);
	 }
}
