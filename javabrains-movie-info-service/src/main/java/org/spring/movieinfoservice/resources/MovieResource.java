/**
 * Movie Info Service return movie information (movie model) for a particular movie
 * Movie model is used to represent a movie ie movie id, movie name and movie description
 */
package org.spring.movieinfoservice.resources;

import org.spring.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
       return new Movie(movieId,movieId+"-name","action movie");

    }
}
