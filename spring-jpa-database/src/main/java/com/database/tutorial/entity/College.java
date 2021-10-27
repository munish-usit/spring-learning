package com.database.tutorial.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "college_details")
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long collegeId;
	@Column(nullable = false)
	private String collegeName;
	
	/**
	 * Rule or Constraint - Each guest teacher can teach in multiple colleges and each college can have multiple guest teachers.
	 * This represent ManytToMany mapping.
	 * We can represent such mapping in SQL in following ways
	 * 1. Create 1 separate table to store guest teacher and college mappings.
	 * 
	 * By default Hibernate create 2 tables guest_teacher_details_colleges and college_details_guest_teachers to store mapping.
	 * mappedBy = it inform hibernate that mapping is already managed by other Entity, so that hibernate doesn't create duplicate mapping table.
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "colleges")
	private List<GuestTeacher> guestTeachers;
}
