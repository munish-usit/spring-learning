package com.sapient.demo.forexservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.demo.forexservice.models.ForexModel;

public interface ForexRepository extends JpaRepository<ForexModel, Long>{

	ForexModel findByFromAndTo(String from, String to);
}
