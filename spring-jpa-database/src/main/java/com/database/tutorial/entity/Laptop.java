package com.database.tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "laptop_details")
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long laptopId;
	private String laptopName;
	private String laptopModel;
	
	/**
	 * Rule or Constraint - Each teacher can have multiple laptops.
	 * This represent OneToMany mapping. 1 teacher having N laptops.
	 * This also represent ManyToOne mapping. N laptops belongs to 1 teacher.
	 * We should maintain only 1 mapping, OneToMany or ManyToOne depending on the use-case.
	 * We can represent such mapping in SQL in following ways
	 * 1. laptop_details store FK(laptop_details.teacher_id) which maps to PK(teacher_details.teacher_id)
	 * 
	 * ManyToOne - Hibernate create FK(laptop_details.teacher_id) which maps to PK(teacher_details.teacher_id)
	 *
	 * */
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
}
