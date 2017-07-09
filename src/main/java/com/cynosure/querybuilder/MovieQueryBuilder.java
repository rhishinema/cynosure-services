package com.cynosure.querybuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.pojo.Movie;
import com.cynosure.services.CommonService;

@Service
public class MovieQueryBuilder {

	@Autowired
	private EntityManager entityManager;
	
	public List<Movie> getAllMovies(){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		
		Root<Movie> movie = query.from(Movie.class);
		return entityManager.createQuery(query.select(movie).orderBy(cb.desc(movie.get("releaseDate")))).getResultList();
	}
	
	public List<Movie> getLatestMovies(){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currDateStr = simpleDateFormat.format(new Date());
		
		Root<Movie> movie = query.from(Movie.class);
		return entityManager.createQuery(query.select(movie).where(cb.greaterThanOrEqualTo(movie.get("releaseDate"), 
				CommonService.getTimeStampFromStr(currDateStr))).orderBy(cb.desc(movie.get("releaseDate")))).getResultList();
	}
}
