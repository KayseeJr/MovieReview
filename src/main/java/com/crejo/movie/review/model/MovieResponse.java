package com.crejo.movie.review.model;

import java.io.Serializable;
import java.util.List;

public class MovieResponse implements Serializable {

	private static final long serialVersionUID = 5083307161724034280L;
	private String name;
	private List<String> genreList;
	private String releaseDate;
	private Long totalScore;
	private Long totalRatings;
	private Double avgScore;
	private String createdOn;

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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

	public Long getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(Long totalRatings) {
		this.totalRatings = totalRatings;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Movie [name =" + name + ", genreList =" + genreList + ", releaseDate =" + releaseDate + ", totalScore ="
				+ totalScore + ", totalRatings =" + totalRatings + ", avgScore =" + avgScore + ", createdOn ="
				+ createdOn + "]";
	}

}
