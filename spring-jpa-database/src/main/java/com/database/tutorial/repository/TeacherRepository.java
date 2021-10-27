package com.database.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.tutorial.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{

	public List<Teacher> findByVehicleIsNull();
	
	public List<Teacher> findByVehicleIsNotNull();
	
	public List<Teacher> findByLaptopsIsNotNull();
}
