package com.database.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.tutorial.entity.GuestTeacher;

public interface GuestTeacherRepository extends JpaRepository<GuestTeacher,Long>{

}
