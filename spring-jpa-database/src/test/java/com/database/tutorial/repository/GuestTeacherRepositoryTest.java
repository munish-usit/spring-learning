package com.database.tutorial.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.tutorial.entity.College;
import com.database.tutorial.entity.GuestTeacher;

@SpringBootTest
public class GuestTeacherRepositoryTest {

	@Autowired
	private GuestTeacherRepository guestTeacherRepository;

	@Test
	public void saveGuestTeachers() {
		
		List<College> colleges = new ArrayList<>();
		colleges.add(College.builder().collegeId(40L).build());
		colleges.add(College.builder().collegeId(41L).build());
		
		GuestTeacher teacher1 = GuestTeacher.builder()
				.firstName("Nikhil1")
				.lastName("Sharma")
				.colleges(colleges)
				.build();
		guestTeacherRepository.save(teacher1);

	}
	
	
	@Test
	public void printAllTeachers() {
		List<GuestTeacher> teacherList =  guestTeacherRepository.findAll();
		System.out.println("guestTeacherList = " + teacherList);
	}




}
