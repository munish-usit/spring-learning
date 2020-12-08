package com.sapient.demo.forexservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.demo.forexservice.models.ForexModel;
import com.sapient.demo.forexservice.service.ForexService;
import com.vils.logging.VilsLogger;

@RestController
public class ForexServiceController {

	@Autowired
	private Environment environment;

	@Autowired
	private ForexService forexService;
	
	private static VilsLogger vilsLogger = VilsLogger.create(ForexServiceController.class);

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ForexModel retrieveExchangeValue	(@PathVariable String from, @PathVariable String to){

		ForexModel forexModel = 
				forexService.findByFromAndTo(from, to);

		forexModel.setPort(
				Integer.parseInt(environment.getProperty("local.server.port")));

		return forexModel;
	}

	@GetMapping("/currency-exchange/ping")
	public String ping() {
		return "pong";
	}
}
