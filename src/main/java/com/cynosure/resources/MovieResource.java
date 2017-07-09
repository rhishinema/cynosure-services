package com.cynosure.resources;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cynosure.pojo.Movie;
import com.cynosure.services.CommonService;
import com.cynosure.services.MovieService;
import com.cynosure.view.ErrorView;
import com.cynosure.view.MoviesView;

@RestController
@RequestMapping("/v1/movie")
public class MovieResource {
	
	@Autowired
	private MovieService movieService;
	
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Object> createMovie(@RequestBody Movie movie){
		String error = "";
		try{
			if(StringUtils.isEmpty(movie.getReleaseDateStr())){
				error = "Invalid Release Date";
				throw new Exception(error);
			}
			movie.setReleaseDate(CommonService.getTimeStampFromStr(movie.getReleaseDateStr()));
			movie = movieService.createMovie(movie);
			if(movie.getMovieId() == null){
				error = "Error while creating movie";
				throw new Exception(error);
			}
			return new ResponseEntity<Object>("movie created", HttpStatus.ACCEPTED);
		}catch(Exception e){
			if(StringUtils.isEmpty(error)){
				error = "Error while creating movie.";
			}
			ErrorView errorView = new ErrorView(error);
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> getAllMovies(){
		try{
			List<Movie> allMoviesList = movieService.getAllMovies();
			return new ResponseEntity<Object>(new MoviesView(allMoviesList), HttpStatus.ACCEPTED);
		}catch(Exception e){
			ErrorView errorView = new ErrorView("Error while getting all movies");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/latest", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> getLatestMovies(){
		try{
			List<Movie> latestMoviesList = movieService.getLatestMovies();
			
			return new ResponseEntity<Object>(new MoviesView(latestMoviesList), HttpStatus.ACCEPTED);
		}catch(Exception e){
			ErrorView errorView = new ErrorView("Error while getting latest movies");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
	
}
