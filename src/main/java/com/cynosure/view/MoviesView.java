package com.cynosure.view;

import java.util.List;

import com.cynosure.pojo.Movie;

public class MoviesView {

	private List<Movie> movies;
	
	public MoviesView(List<Movie> movies){
		this.movies = movies;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
