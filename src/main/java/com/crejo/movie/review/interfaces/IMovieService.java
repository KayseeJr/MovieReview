package com.crejo.movie.review.interfaces;

import java.util.List;
import java.util.Map;

import com.crejo.movie.review.model.MovieEntity;


public interface IMovieService {

	public Boolean addMovie(String movieName, List<String> genreList, String releaseDate) throws Exception;

	public Map<String, MovieEntity> getMovieMap();

}
