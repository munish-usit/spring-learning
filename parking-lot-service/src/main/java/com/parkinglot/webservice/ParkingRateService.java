package com.parkinglot.webservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.core.ParkingService;
import com.parkinglot.model.Bill;
import com.parkinglot.model.ParkingTicket;

@RestController
public class ParkingRateService {

	@Autowired
	private ParkingService parkingService;	
	
	@RequestMapping(method=RequestMethod.POST , value="/parking")
	public void parkIn(@RequestBody ParkingTicket parkingTicket) {
		parkingService.save(parkingTicket);
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/parking/{id}")
	public void parkOut(@PathVariable("id") String id) {
		parkingService.update(id);
	}
	
	@RequestMapping(method=RequestMethod.GET , value="/parking/{id}")
	public Bill generateBill(@PathVariable("id") String id) {
		return parkingService.generateBill(id);
	}
	
	
	
	
	
	
}
