package org.spring.moviecatalogservice.services;

import java.util.Arrays;
import java.util.List;

import org.spring.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class RatingService {

	@Autowired
	private RestTemplate restTemplate;
	
	// BulkHead pattern
	@HystrixCommand(fallbackMethod = "getFallbackUserRatings",
			threadPoolKey = "ratingPool",
			threadPoolProperties = {
					@HystrixProperty(name="coreSize",value="20"),  // thread pool of size 20.. At a time there will be max 20 thread to execute the request
					@HystrixProperty(name="maxQueueSize",value="10") // there can be max 10 requests in waiting
				}
			)
	public List<Rating> getUserRatings(@PathVariable String userId) {
		String ratingurl = "http://ratings-data-service/ratingsdata/users/"+userId;
		ratingurl = "http://localhost:8083/ratingsdata/users/"+userId;;
		Rating[] ratingObj = restTemplate.getForObject(ratingurl,Rating[].class);
		List<Rating> ratings = Arrays.asList(ratingObj);
		return ratings;
	}
	
	public List<Rating> getFallbackUserRatings(@PathVariable String userId) {
		
		List<Rating> ratings = Arrays.asList(new Rating("No Movie",-1));
		return ratings;
	}
}
