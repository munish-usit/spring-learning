package com.springcloud.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcloud.tutorial.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
