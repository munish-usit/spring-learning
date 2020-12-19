package com.tutorials.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorials.database.model.UserDetails;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {

}
