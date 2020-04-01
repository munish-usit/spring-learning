package com.parkinglot.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.core.BillService;
import com.parkinglot.core.ParkingService;
import com.parkinglot.model.Bill;
import com.parkinglot.model.ParkingTicket;

@RestController
@RequestMapping("/reports")
public class ReportingService {

	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private BillService billService;
	
	@RequestMapping("/bills")
	public List<Bill> getAllBills() {
		return billService.getAllBills();
	}
	
	
	@RequestMapping("/bills/{id}")
	public Bill getBill(@PathVariable("id") String id) {
		return billService.getBill(id);
	}
	
	@RequestMapping("/parking")
	public List<ParkingTicket> getAllParkingTickets() {
		return parkingService.getAllParkingTickets();
	}
	
	
	@RequestMapping("/parking/{id}")
	public ParkingTicket getParkingTicket(@PathVariable("id") String id) {
		return parkingService.getParkingTicket(id);
		
	}
	

}
