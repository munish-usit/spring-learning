package com.database.tutorial.entity;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
		name = "student_details",
		uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = { "email_address" })
)
public class Student {
	
	/**
	 * Id - uniquely identifies the rows in the table. Equivalent to Primary Key
	 */
	@Id
	/**
	 * SequenceGenerator - create sequence generator in the SQL to automatically update primary key value for every insert operation.
	 * Sequence in SQL keep track of the inserted rows.
	 */
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	/**
	 * GeneratedValue - It informs SQL that value is getting generated automatically based on GenerationType strategy.
	 */
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)
	private Long studentId;
	private String firstName;
	private String lastName;
	
	/**
	 * Columns - used to change column name and define column specific properties.
	 */
	@Column(name = "email_address", nullable = false)
	private String emailId;
	
	private int age;
	
	/**
	 * Embedded - used to include user defined Object or ValueType in the Entity.
	 * @Embeddable object or class doesn't have corresponding table in SQL as it is not marked as Entity.
	 * Attributes of @Embeddable class(Guardian) will become columns of Entity class(Student)
	 * SQL doesn't support MultiValued or JSON data types.
	 */
	@Embedded
	private Guardian guardian;
	
	/**
	 * It will pick corresponding column names = street_name, city_name, pin_code
	 * 
	 */
	@Embedded
	private Address homeAddress;
	
	/**
	 * Entity or Table have multiple instance of same @Embeddable class (Address)
	 * @AttributeOverrides is used to differentiate the column names for multiple instances.
	 * homeAddress column names = street_name, city_name, pin_code
	 * officeAddress column names = office_street_name, office_city_name, office_pin_code
	 */
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "street" , column = @Column(name = "office_street_name")) ,
		@AttributeOverride(name = "city" , column = @Column(name = "office_city_name")) ,
		@AttributeOverride(name = "pinCode" , column = @Column(name = "office_pin_code")) 
	})
	private Address officeAddress;
	
	/**
	 * SQL doesn't support MultiValued columns.
	 * To support multiple values, it will create separate table and store student_id and topics mappings.
	 * Default Table name = EntityName("student_details")_VariableName("topics") i.e student_details_topics
	 * Default Column name = EntityName("student_details")_IdColumn("student_id")
	 * Hibernate automatically insert mapping in student_topics table, while inserting record in student_details table.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	/**
	 * @JoinTable - To override the default table name and default join column.
	 * @JoinColumn - To override default join column name and property.
	 * @JoinColumn("student_id") act as Foreign key in mapping table ("student_topics") 
	 * And refers to the primary key("student_id") in main Entity table("student_details")
	 */
	@JoinTable(
			name = "student_topics",
			joinColumns = @JoinColumn(name = "student_id")
	)
	private Collection<String> topics;
}
