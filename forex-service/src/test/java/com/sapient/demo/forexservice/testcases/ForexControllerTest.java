package com.sapient.demo.forexservice.testcases;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sapient.demo.forexservice.controllers.ForexServiceController;
import com.sapient.demo.forexservice.models.ForexModel;
import com.sapient.demo.forexservice.service.ForexService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(ForexServiceController.class)
public class ForexControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ForexService forexService;
	
	//@Test
	public void testPing() {
		try {
			mvc.perform(get("/currency-exchange/ping")
					  .accept(MediaType.TEXT_PLAIN))
				      .andDo(print())
				      .andExpect(status().isOk())
				      .andExpect(content().string(containsString("pong")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			      
	}
	
	//@Test
	public void testCurrency() {
		try {
			ForexModel model = new ForexModel(1L, "DEN", "INR", BigDecimal.valueOf(50));
			Mockito.when(forexService.findByFromAndTo("DEN", "INR")).thenReturn(model);
			
			mvc.perform(get("/currency-exchange/from/DEN/to/INR")
					  .accept(MediaType.TEXT_PLAIN))
				     // .andDo(print())
				      .andExpect(status().isOk())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			      
	}
	
	
}
