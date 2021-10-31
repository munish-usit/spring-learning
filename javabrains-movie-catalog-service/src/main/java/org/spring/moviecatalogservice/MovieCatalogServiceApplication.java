package org.spring.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServiceApplication {


	@Bean
	//@LoadBalanced
	public RestTemplate getRestTemplate() {
		// Setting connection timeout for the rest template	
		HttpComponentsClientHttpRequestFactory clientFactory = new HttpComponentsClientHttpRequestFactory();
		clientFactory.setConnectTimeout(3000);
		return new RestTemplate(clientFactory);
	}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
