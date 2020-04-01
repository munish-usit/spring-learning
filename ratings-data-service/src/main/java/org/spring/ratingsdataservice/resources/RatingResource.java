/**
 * Rating  Service return rating information (movie model) for a particular movie
 * Rating model is used to represent rating info for a particular movie id
 * Eg Movie id 12 has rating 4
 */
package org.spring.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.spring.ratingsdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	@RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

	
	  @RequestMapping("/users/{userId}") 
	  public List<Rating> getUserRatings(@PathVariable("userId") String userId) { 
		  List<Rating> ratings = Arrays.asList(
				  new Rating("123", 4),
				  new Rating("124",5),
				  new Rating("125",6));
		  return ratings;

	  }
	 
}
