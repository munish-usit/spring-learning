package com.parkinglot.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parkinglot.model.Bill;


@Service
public class BillRepository   {

	List<Bill> repo = new ArrayList<Bill>();
	
	public void save(Bill bill) {
		repo.add(bill);
	}
	
	public Bill findById(String id) {
		
		for(Bill bill : repo) {
			if(bill.getId().equals(id)) return bill;
		}
		return null;
	}
	
	public List<Bill> getAll() {
		return repo;
	}
}
