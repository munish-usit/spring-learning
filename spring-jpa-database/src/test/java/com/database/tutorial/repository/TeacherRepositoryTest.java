package com.database.tutorial.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.tutorial.entity.Laptop;
import com.database.tutorial.entity.Teacher;
import com.database.tutorial.entity.Vehicle;

@SpringBootTest
public class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void saveTeachers() {
		Teacher teacher1 = Teacher.builder().firstName("Nikhil1").lastName("Sharma").build();
		teacherRepository.save(teacher1);

		Teacher teacher2 = Teacher.builder().firstName("Nikhil2").lastName("Sharma").build();
		teacherRepository.save(teacher2);

		Teacher teacher3 = Teacher.builder().firstName("Nikhil3").lastName("Sharma").build();
		teacherRepository.save(teacher3);
	}
	
	@Test
	public void saveTeachersHavingVehicles() {
		
		Vehicle vehicle4 = Vehicle.builder().vehicleId(14L).build();
		Teacher teacher4 = Teacher.builder().firstName("Rahul1").lastName("Sharma").vehicle(vehicle4).build();
		teacherRepository.save(teacher4);

		Vehicle vehicle5 = Vehicle.builder().vehicleId(15L).build();
		Teacher teacher5 = Teacher.builder().firstName("Rahul2").lastName("Sharma").vehicle(vehicle5).build();
		teacherRepository.save(teacher5);
	}
	
	@Test
	public void saveTeachersHavingLaptops() {
		
		List<Laptop> laptops = new ArrayList<>();
		laptops.add(Laptop.builder().laptopId(23L).build());
		laptops.add(Laptop.builder().laptopId(24L).build());
		
		Teacher teacher6 = Teacher.builder()
				.firstName("Rahul3")
				.lastName("Sharma")
				.laptops(laptops)
				.build();
		teacherRepository.save(teacher6);
	}


	@Test
	public void printTeachersNotHavingVehicles() {
		List<Teacher> teacherList =  teacherRepository.findByVehicleIsNull();
		System.out.println("teacherList = " + teacherList);
	}
	
	@Test
	public void printTeachersHavingVehicles() {
		List<Teacher> teacherList =  teacherRepository.findByVehicleIsNotNull();
		System.out.println("teacherList = " + teacherList);
	}
	
	@Test
	public void printTeachersHavingLaptops() {
		List<Teacher> teacherList =  teacherRepository.findByLaptopsIsNotNull();
		System.out.println("teacherList = " + teacherList);
	}
	
	@Test
	public void printAllTeachers() {
		List<Teacher> teacherList =  teacherRepository.findAll();
		System.out.println("teacherList = " + teacherList);
	}




}
