package org.spring.moviecatalogservice.services;



import org.spring.moviecatalogservice.models.Movie;
import org.spring.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;
	
	// Circuit Breaker
	@HystrixCommand(fallbackMethod = "getFallbackCatalog",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"), // timeout
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="100"), // n request
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"), // error request
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"), // sleep time window
			})
	public Movie getCatalog(Rating rating) {
		String url = "http://movie-info-service/movies/"+rating.getMovieId();
		url = "http://localhost:8082/movies/"+rating.getMovieId();
		Movie m = restTemplate.getForObject(url, Movie.class);
		return m;
	}
	
	public Movie getFallbackCatalog(Rating rating) {
		return new Movie("No movie","","");
	}
}
