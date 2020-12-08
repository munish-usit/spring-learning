package com.sapient.demo.forexservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.demo.forexservice.models.ForexModel;
import com.sapient.demo.forexservice.repositories.ForexRepository;

@Service
public class ForexService {

	@Autowired
	ForexRepository forexRepository;
	
	public ForexModel findByFromAndTo(String from, String to) {
		return forexRepository.findByFromAndTo(from, to);
	}
}
