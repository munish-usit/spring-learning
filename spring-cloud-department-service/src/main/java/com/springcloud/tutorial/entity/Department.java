package com.springcloud.tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "department_details")
public class Department {

	@Id
	private Long departmentId;
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	// just to check server port during feign client load balancing call
	private String departmentPort;

}
