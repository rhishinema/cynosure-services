package com.cynosure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cynosure.dao.MovieRepository;
import com.cynosure.pojo.Movie;
import com.cynosure.querybuilder.MovieQueryBuilder;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieQueryBuilder movieQueryBuilder;
	
	@Transactional
	public Movie createMovie(Movie movie){
		return movieRepository.save(movie);
	}
	
	public List<Movie> getAllMovies(){
		return movieQueryBuilder.getAllMovies();
	}
	
	public List<Movie> getLatestMovies(){
		return movieQueryBuilder.getLatestMovies();
	}
}
