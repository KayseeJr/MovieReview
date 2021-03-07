package com.crejo.movie.review.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crejo.movie.review.interfaces.IMovieService;
import com.crejo.movie.review.model.MovieEntity;

public class MovieService implements IMovieService {

	public Map<String, MovieEntity> movieMap = new HashMap<>();

	public Map<Integer, List<Integer>> movieReviewIdListMap = new HashMap<>();

	public Map<String, List<Integer>> genreMovieList = new HashMap<>();

	@Override
	public Boolean addMovie(String movieName, List<String> genreList, Date releaseDate) {
		MovieEntity entity = new MovieEntity(movieName, genreList, releaseDate);
		movieMap.put(movieName, entity);
		return true;
	}

	@Override
	public Map<String, MovieEntity> getMovieMap() {
		return movieMap;
	}

}
