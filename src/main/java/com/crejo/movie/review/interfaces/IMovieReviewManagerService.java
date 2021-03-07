package com.crejo.movie.review.interfaces;

import java.util.List;

import com.crejo.movie.review.model.MovieResponse;

public interface IMovieReviewManagerService {

	public Boolean addUser(String userName) throws Exception;

	public Boolean addMovie(String movieName, List<String> genreList, String releaseDate) throws Exception;

	public Boolean addMovieReview(String userName, String movieName, Integer rating) throws Exception;

	public List<MovieResponse> getTopNMoviesByCriteria(Integer n, String userType, Integer releaseYear, String genre,
			Boolean isAvg);

	public Double getAverageMovieReviewScoreByCriteria(String movieName, Integer releaseYear, String genre);

}
