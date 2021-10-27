package com.database.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.tutorial.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long>{

}
