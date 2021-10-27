package com.database.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.tutorial.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

}
