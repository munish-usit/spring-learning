package com.database.tutorial.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

/**
 * @Embeddable object or class doesn't have corresponding table in SQL as it is not marked as Entity.
 * Attributes of @Embeddable class(Guardian) will become columns of Entity class(Student)
 * SQL doesn't support MultiValued or JSON data types.
 */
@Embeddable
public class Address {

	/**
	 * @Column - used to override the default column name. Column will be created as part of main Entity table.
	 */
	@Column(name = "street_name")
	private String street;
	@Column(name = "city_name")
	private String city;
	private String pinCode;
}
