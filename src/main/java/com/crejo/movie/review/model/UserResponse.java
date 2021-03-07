package com.crejo.movie.review.model;

import java.io.Serializable;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = -1441362633716748202L;
	private String name;
	private Long ratingCount;
	private String type;
	private String createdOn;

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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "User [name =" + name + ", ratingCount =" + ratingCount + ", type =" + type + ", createdOn =" + createdOn
				+ "]";
	}

}
