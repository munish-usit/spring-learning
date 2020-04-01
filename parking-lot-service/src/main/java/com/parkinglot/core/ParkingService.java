package com.parkinglot.core;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.Bill;
import com.parkinglot.model.ParkingDuration;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.repo.BillRepository;
import com.parkinglot.repo.ParkingTicketRepository;

@Service
public class ParkingService {

	@Autowired
	ParkingTicketRepository parkingTicketRepo;
	
	@Autowired
	BillRepository billRepo;
	
	@Autowired
	RateCalculator rateCalculator;
	
	public ParkingTicket getParkingTicket(String id) {
		//ParkingTicket t = new ParkingTicket("123",new ParkingLot(12), new Vehicle(11, "creta"), new UserInfo("munish", "noida"), new ParkingDuration(LocalDateTime.now(), LocalDateTime.now()));
		//return t;
		return parkingTicketRepo.findById(id);
	}
	
	public List<ParkingTicket> getAllParkingTickets() {
		return parkingTicketRepo.getAll();
	}
	
	public void save(ParkingTicket parkingTicket) {
		ParkingDuration duration = new ParkingDuration(LocalDateTime.now(), null);
		parkingTicket.setDuration(duration);
		parkingTicketRepo.save(parkingTicket);
	}
	
	public void update(String id) {
		//parkingTicketRepo.save(parkingTicket);
		ParkingTicket ticket  = parkingTicketRepo.findById(id);
		if(ticket != null) {
			ticket.getDuration().setCheckOut(LocalDateTime.now());
			parkingTicketRepo.save(ticket);
		}
	}
	
	public Bill generateBill(String id) {
		ParkingTicket ticket  = parkingTicketRepo.findById(id);
		int rate = -1;
		if(ticket != null) {
			rate = rateCalculator.calculateParkingRate(ticket.getDuration().getCheckIn(), ticket.getDuration().getCheckOut());
		}
		Bill bill = new Bill(id+"bill",ticket.getId(),rate);
		billRepo.save(bill);
		return bill;
	}
	
}
