package com.database.tutorial.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.tutorial.entity.College;

@SpringBootTest
 class CollegeRepositoryTest {

	@Autowired
	private CollegeRepository collegeRepository;
	
	@Test
	void saveColleges() {
		College college1 = College.builder().collegeName("college1").build();
		collegeRepository.save(college1);
		
		College college2 = College.builder().collegeName("college2").build();
		collegeRepository.save(college2);
	}
	
	@Test
	void getColleges() {
		List<College> colleges = collegeRepository.findAll();
		System.out.println("collegeList = "+colleges);
	}
}
