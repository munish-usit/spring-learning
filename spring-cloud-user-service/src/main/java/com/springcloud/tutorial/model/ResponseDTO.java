package com.springcloud.tutorial.model;

import com.springcloud.tutorial.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {

	private User user;
	private Department department;
}
