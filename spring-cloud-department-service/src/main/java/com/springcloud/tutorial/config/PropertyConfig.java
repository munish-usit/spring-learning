package com.springcloud.tutorial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
@RefreshScope
public class PropertyConfig {

	@Value("${message}")
	private String message;
	
	@Value("${developer}")
	private String developer;
	
	@Value("${role}")
	private String role;
	
	@Value("${company}")
	private String company;
	
	@Value("${server.port}")
	private String serverPort;
}
