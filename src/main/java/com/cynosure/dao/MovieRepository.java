package com.cynosure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cynosure.pojo.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{

	
}
