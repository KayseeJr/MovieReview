package com.crejo.movie.review.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MovieEntity implements Serializable {

	private static final long serialVersionUID = -4262567922829344684L;
	private String name;
	private List<String> genreList;
	private Date releaseDate;
	private Date createdOn;

	public MovieEntity(String name, List<String> genreList, Date releaseDate) {
		this.name = name;
		this.genreList = genreList;
		this.releaseDate = releaseDate;
		this.createdOn = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<String> genreList) {
		this.genreList = genreList;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Movie [name =" + name + ", genreList =" + genreList + ", releaseDate =" + releaseDate + ", createdOn ="
				+ createdOn + "]";
	}

}
