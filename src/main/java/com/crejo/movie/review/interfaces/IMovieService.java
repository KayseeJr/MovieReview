package com.crejo.movie.review.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.crejo.movie.review.model.MovieEntity;

public interface IMovieService {

	public Boolean addMovie(String movieName, List<String> genreList, Date releaseDate);

	public Map<String, MovieEntity> getMovieMap();

}
