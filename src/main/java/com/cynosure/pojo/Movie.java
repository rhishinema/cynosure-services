package com.cynosure.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cynosure.services.CommonService;

@Entity
@Table(name="t_movie_details", schema="[cynosure-schema]")
public class Movie {

	@Id
	@Column(name="MOVIE_ID")
	@GeneratedValue
	private Long movieId;
	
	@Column(name="MOVIE_NAME")
	private String movieName;
	
	@Column(name="MOVIE_DESC")
	private String movieDesc;
	
	@Column(name="RELEASE_DATE")
	private Timestamp releaseDate;
	
	@Column(name="MOVIE_BANNER_URL")
	private String moviebannerUrl;
	
	@Column(name="CASTING_DETAILS")
	private String castingDetails;
	
	@Column(name="GENRE")
	private String genre;
	
	@Transient
	private String releaseDateStr;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public Timestamp getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMoviebannerUrl() {
		return moviebannerUrl;
	}

	public void setMoviebannerUrl(String moviebannerUrl) {
		this.moviebannerUrl = moviebannerUrl;
	}

	public String getCastingDetails() {
		return castingDetails;
	}

	public void setCastingDetails(String castingDetails) {
		this.castingDetails = castingDetails;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getReleaseDateStr() {
		if(releaseDate != null){
			releaseDateStr = CommonService.getTimeStampStr(releaseDate);
		}
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}
}
