package com.parkinglot.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parkinglot.model.ParkingTicket;

@Service
public class ParkingTicketRepository  {

	List<ParkingTicket> repo = new ArrayList<ParkingTicket>();
	
	public void save(ParkingTicket parkingTicket) {
		repo.add(parkingTicket);
	}
	
	public ParkingTicket findById(String id) {
		
		for(ParkingTicket ticket : repo) {
			if(ticket.getId().equals(id)) return ticket;
		}
		return null;
	}
	
	public List<ParkingTicket> getAll() {
		return repo;
	}
	
}
