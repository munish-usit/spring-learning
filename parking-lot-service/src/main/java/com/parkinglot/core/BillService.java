package com.parkinglot.core;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.Bill;

import com.parkinglot.repo.BillRepository;


@Service
public class BillService {

	@Autowired
	BillRepository billRepo;
	
	
	public void saveBill(Bill bill) {
		billRepo.save(bill);
	}
	
	public Bill getBill(String id) {
		return billRepo.findById(id);
	}
	
	public List<Bill> getAllBills() {
		return billRepo.getAll();
	}
	
	
}
