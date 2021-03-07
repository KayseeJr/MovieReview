package com.crejo.movie.review.mappers;

import java.text.SimpleDateFormat;

import com.crejo.movie.review.model.MovieEntity;
import com.crejo.movie.review.model.MovieResponse;

public class MovieMapper {

	public static MovieResponse mapEntityToResponse(MovieEntity entity) {
		MovieResponse response = new MovieResponse();
		response.setName(entity.getName());
		response.setGenreList(entity.getGenreList());
		response.setReleaseDate(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(entity.getReleaseDate()));
		response.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(entity.getCreatedOn()));
		return response;
	}

}
