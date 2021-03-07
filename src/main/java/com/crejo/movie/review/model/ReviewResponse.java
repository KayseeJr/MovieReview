package com.crejo.movie.review.model;

import java.io.Serializable;

public class ReviewResponse implements Serializable {

	private static final long serialVersionUID = 1555032875000637947L;
	private Long id;
	private String movieName;
	private String userName;
	private String userType;
	private Integer score;
	private String createdOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Review [id =" + id + ", movieName =" + movieName + ", userName =" + userName + ", userType =" + userType
				+ ", score =" + score + ", createdOn =" + createdOn + "]";
	}

}
