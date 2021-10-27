package com.security.tutorial.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.security.tutorial.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
