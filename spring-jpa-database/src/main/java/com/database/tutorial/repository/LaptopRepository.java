package com.database.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.tutorial.entity.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
