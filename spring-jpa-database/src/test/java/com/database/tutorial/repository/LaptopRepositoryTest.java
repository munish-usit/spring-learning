package com.database.tutorial.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.tutorial.entity.Laptop;
import com.database.tutorial.entity.Teacher;

@SpringBootTest
 class LaptopRepositoryTest {

	@Autowired
	private LaptopRepository laptopRepository;


	@Test
	void saveLaptops() {
		
		Laptop laptop1 = Laptop.builder().laptopId(1L).laptopName("Laptop1").laptopModel("Laptop1").build();
		laptopRepository.save(laptop1);
		
		Laptop laptop2 = Laptop.builder().laptopName("Laptop2").laptopModel("Laptop2").build();
		laptopRepository.save(laptop2);
		
		Laptop laptop3 = Laptop.builder().laptopName("Laptop3").laptopModel("Laptop3").build();
		laptopRepository.save(laptop3);
	}
	
	@Test
	void saveLaptopsWithTeacher() {
		
		Laptop laptop4 = Laptop.builder()
				.laptopId(1L)
				.laptopName("Laptop4")
				.laptopModel("Laptop4")
				.teacher(Teacher.builder().teacherId(10L).build())
				.build();
		laptopRepository.save(laptop4);
		
		Laptop laptop5 = Laptop.builder()
				.laptopName("Laptop5")
				.laptopModel("Laptop5")
				.teacher(Teacher.builder().teacherId(10L).build())
				.build();
		laptopRepository.save(laptop5);
		
	}
	
	@Test
	void getLaptops() {
		List<Laptop> laptops = laptopRepository.findAll();
		System.out.println("laptopsList = "+laptops);
	}



}
