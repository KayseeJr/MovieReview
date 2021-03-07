package com.crejo.movie.review.model;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5890800549111798385L;
	private String name;
	private Long ratingCount;
	private String type;
	private Date createdOn;

	public UserEntity(String name) {
		this.name = name;
		this.ratingCount = 0l;
		this.createdOn = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(Long ratingCount) {
		this.ratingCount = ratingCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "User [name =" + name + ", ratingCount =" + ratingCount + ", type =" + type + ", createdOn =" + createdOn
				+ "]";
	}

}
