package com.sapient.demo.currencyconversionservice.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sapient.demo.currencyconversionservice.models.CurrencyConversionModel;
import com.sapient.demo.currencyconversionservice.service.CurrencyExchangeServiceProxy;


@RestController
@RefreshScope
public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @Value("${msg:Config Server is not working. Please check...}")
	 private String msg;
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;


	@HystrixCommand(fallbackMethod = "defaultFallbackMethod")
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionModel convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionModel response = proxy.getExchangeValue(from, to);

		logger.info("{}", response);

		return new CurrencyConversionModel(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	
	public CurrencyConversionModel defaultFallbackMethod(String from, String to, BigDecimal quantity) {
		logger.info("{}", "hystrix fallback method");
		return new CurrencyConversionModel();
	}


	@GetMapping("/ping")
	public String ping() {
		logger.info("{}", "pong");
		return "pong";
	}
	
	@GetMapping("/config/message")

	public String message() {
		logger.info("{}", this.msg);
		return this.msg;
	}
	
	@GetMapping("/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{}", e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			logger.error("{}", "Exception - "+stackTrace);
			response = stackTrace;
		}

		return response;
	}
}
