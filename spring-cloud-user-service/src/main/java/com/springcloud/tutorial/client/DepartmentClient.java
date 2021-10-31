package com.springcloud.tutorial.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcloud.tutorial.model.Department;

/**
 * https://javatechonline.com/how-to-implement-feign-client-in-spring-boot-microservices/
 * 
 * @FeignClient(name = "department-service") = Feign is Discovery Server Aware and use service-name to identify the url.
 * Feign is a declarative REST Client. It makes writing web service clients easier. 
 * Moreover, it internally generates a Proxy class at runtime using Dynamic Proxy Pattern. 
 * It actually gets Application name(Service Instance) from Eureka and supports Making HTTP call.
 * With Eureka Server, Feign client also support client side Load Balancing (No need for Ribbon)
 * Internally spring-cloud-starter-eureka-client have spring-cloud-starter-loadbalancer dependency
 *
 * If we are not using Eureka Discovery Client/Server, then we have to provide custom url
 * @FeignClient(name = "department-service", url = "${department.service.url}"
 * Without Discovery Client/Server, we have to use Ribbon for Client side Load Balancing
 * 
 * Feign client also works well without Eureka Client/Server(Service Registry) and Ribbon(Client Side Load Balancing)
 */
@FeignClient(name = "department-service")
public interface DepartmentClient {

	@GetMapping("/departments/{id}")
	public Department getDepartment(@PathVariable("id") Long departmentId);
}
