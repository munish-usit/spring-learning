package com.database.tutorial.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "teacher_details")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teacherId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	
	/**
	 * Rule or Constraint - Each teacher can own only 1 unique office vehicle. 
	 * This represent OneToOne mapping which is unique. 1 teacher 1 vehicle. 
	 * We can represent such mapping in SQL in following ways
	 * 1. @Embeddable - teacher_details have complete vehicle information (vehicle_name,vehicle_model). This violate normalization principle.
	 * 2. teacher_details store just unique vehicle_id and vehicle information is stored in separate vehilce_details information.
	 * 2. FK(teacher_details.vehicle_id) refers to PK(vehicle_details.vehicle_id) 
	 * 3. vehicle_details store unique teacher_id to identify vehicle belongs to which teacher.
	 * 3. FK(vehicle_details.teacher_id) refers to PK(teacher_details.teacher_id)
	 * Out of 2 and 3 approach, we have to see the use case and then store the mapping either in teacher_details or in vehcile_details table
	 * 
	 * Teacher Data Save Behavior
	 * While saving Teacher Data we also need to provide Vehicle Object (only vehcileId is required, other attributes optional)
	 * While saving Teacher Data, if we don't add Vehicle Object, then for that record vehicle_number is NULL.
	 * While saving Teacher Data, if we provide incorrect Vehicle mapping, then it will throw FK constraint error
	 * 
	 * Teacher Data Get Behavior
	 * While fetching Teacher Data, Vehicle object information is automatically embedded.
	 * Hibernate automatically perform 1 additional query on vehicle_details to get matching vehicle details for a particular teacher record.
	 * There is no JOIN operation.
	 */
	@OneToOne
	/**
	 * This is used to override the default FK column name
	 */
	@JoinColumn(name = "vehicle_number")
	private Vehicle vehicle;
	
	
	/**
	 * Rule or Constraint - Each teacher can have multiple laptops.
	 * This represent OneToMany mapping. 1 teacher having N laptops.
	 * We can represent such mapping in SQL in following ways
	 * 1. Create separate mapping table and store teacher_id and laptop_id
	 * 
	 * OneToMany - Hibernate create separate mapping table to store teacher_id and laptop_id mappings.
	 * Default Table name = TableName("teacher_details")_VariableName("laptops") i.e teacher_details_laptops
	 * Default Join Column name = EntityName("teacher")_IdColumn("teacher_id") i.e teacher_teacher_id
	 * Default InverseJoin Column name = VariableName("laptops")_IdColumn("laptop_id") i.e laptops_laptop_id
	 * 
	 * Teacher Data Save Behavior
	 * While saving Teacher Data we also need to provide List of Laptops Object (only laptopId is required, other attributes optional)
	 * While saving Teacher Data, Hibernate automatically save teacher_id and laptop_id mapping in teacher_laptop table
	 * 
	 * Teacher Data Get Behavior
	 * While fetching Teacher Data, by default Laptops object information is not automatically embedded.
	 * FetchType.EAGER = Hibernate perform INNER JOIN on teacher_laptops and laptop_details tables to get Laptops Object information for particular teacher record.
	 * There is INNER JOIN operation required
	 * 
	 * */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "teacher_laptops",
			joinColumns = @JoinColumn(name = "teacher_id"),
			inverseJoinColumns = @JoinColumn(name = "laptop_id")
	)
	private List<Laptop> laptops;
}
