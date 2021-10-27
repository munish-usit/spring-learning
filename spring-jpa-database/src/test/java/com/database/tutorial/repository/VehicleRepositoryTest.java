package com.database.tutorial.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.tutorial.entity.Vehicle;

@SpringBootTest
public class VehicleRepositoryTest {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Test
	public void saveVehicles() {
		Vehicle vehicle1 = Vehicle.builder().vehicleName("Vehicle1").vehicleModel("Model1").build();
		vehicleRepository.save(vehicle1);

		Vehicle vehicle2 = Vehicle.builder().vehicleName("Vehicle2").vehicleModel("Model2").build();
		vehicleRepository.save(vehicle2);

		Vehicle vehicle3 = Vehicle.builder().vehicleName("Vehicle3").vehicleModel("Model3").build();
		vehicleRepository.save(vehicle3);
	}
	
	@Test
	public void saveVehiclesWithId() {
		Vehicle vehicle4 = Vehicle.builder().vehicleId(40L).vehicleName("Vehicle4").vehicleModel("Model4").build();
		vehicleRepository.save(vehicle4);

		Vehicle vehicle5 = Vehicle.builder().vehicleId(50L).vehicleName("Vehicle5").vehicleModel("Model5").build();
		vehicleRepository.save(vehicle5);
	}

	@Test
	public void printAllVehicles() {
		List<Vehicle> vehicleList =  vehicleRepository.findAll();
		System.out.println("vehicleList = " + vehicleList);
	}




}
