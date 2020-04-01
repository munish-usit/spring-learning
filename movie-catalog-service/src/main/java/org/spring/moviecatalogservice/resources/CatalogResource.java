/**
 * Catalog Service return list of all rated movies for a particulat user.
 * Eg User 1 has rated 4 movies with id 1,2,3,4.
 * For each movie it will fetch movie information 
 * Collect all information and return to the user
 */
package org.spring.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.spring.moviecatalogservice.models.CatalogItem;
import org.spring.moviecatalogservice.models.Movie;
import org.spring.moviecatalogservice.models.Rating;
import org.spring.moviecatalogservice.services.MovieInfoService;
import org.spring.moviecatalogservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		//hard coded
		//return Collections.singletonList(new CatalogItem("transformer", "action movie", 5));
		
		// Logic
		// List of all rated movies for a particular user.
		// For each rated movie fetch movie information
		// Collect information and return to the user.
		
		List<CatalogItem> items = new ArrayList<CatalogItem>();
		
		/**
		String ratingurl = "http://ratings-data-service/ratingsdata/users/"+userId;
		ratingurl = "http://localhost:8083/ratingsdata/users/"+userId;;
		Rating[] ratingObj = restTemplate.getForObject(ratingurl,Rating[].class);
		List<Rating> ratings = Arrays.asList(ratingObj);
		
		for(Rating rating: ratings) {
			String url = "http://movie-info-service/movies/"+rating.getMovieId();
			url = "http://localhost:8082/movies/"+rating.getMovieId();
			Movie m = restTemplate.getForObject(url, Movie.class);
			items.add(new CatalogItem(m.getName(), m.getDescription(), rating.getRating()));
		}
		
		**/
		
		// Approach 2  -- Granular Refactoring with Service class
		List<Rating> ratings = ratingService.getUserRatings(userId);
		for(Rating rating: ratings) {
			Movie m = movieInfoService.getCatalog(rating);
			items.add(new CatalogItem(m.getName(), m.getDescription(), rating.getRating()));
		}
		
		
		return items;
	}
	
	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
		
		return Collections.singletonList(new CatalogItem("No movie", "", -1));
			
	}
}
