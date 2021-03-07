package com.crejo.movie.review.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public Boolean addMovie(String movieName, List<String> genreList, String releaseDate) throws Exception {
		if(movieName == null || movieName.isEmpty()) {
			throw new Exception("Movie already exists");
		}
		if(genreList == null || genreList.isEmpty()) {
			throw new Exception("Movie genre can't be empty");
		}
		Date movieDate;
		try {
			movieDate = new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);
		} catch (ParseException e) {
			throw new Exception("Invalid release year format.");
		}
		
		if (movieMap.containsKey(movieName)) {
			throw new Exception("Movie already exists");
		}

		MovieEntity entity = new MovieEntity(movieName, genreList, movieDate);
		movieMap.put(movieName, entity);
		return true;
	}
	
	@Override
	public Map<String, MovieEntity> getMovieMap() {
		return movieMap;
	}

}
