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
@Table(name = "guest_teacher_details")
public class GuestTeacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teacherId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	
	/**
	 * Rule or Constraint - Each guest teacher can teach in multiple colleges and each college can have multiple guest teachers.
	 * This represent ManytToMany mapping.
	 * We can represent such mapping in SQL in following ways
	 * 1. Create 1 separate table to store guest teacher and college mappings.
	 * 
	 * In ManyToMany separate table is created, thus JOIN operation is performed during fetch
	 */
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<College> colleges;
	
	/**
	 * This is to avoid stack overflow error in Bi-directional ManyToMany mapping.
	 * GuestTeacher.toString() will internally call College.toString() which will internally call GuestTeacher.toString()..thus creating infinite loop.
	 * Custom toString() implementation is given (not printing List<College> college) to avoid loop
	 */
	@Override
	public String toString() {
	    return "Teacher[id=" + teacherId + ", name=" + firstName  + "]";
	}
}
