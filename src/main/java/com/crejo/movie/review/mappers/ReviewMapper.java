package com.crejo.movie.review.mappers;

import java.text.SimpleDateFormat;

import com.crejo.movie.review.model.ReviewEntity;
import com.crejo.movie.review.model.ReviewResponse;


public class ReviewMapper {
	
	public static ReviewResponse mapEntityToResponse(ReviewEntity entity) {
		ReviewResponse response = new ReviewResponse();
		response.setId(entity.getId());
		response.setMovieName(entity.getMovieName());
		response.setUserName(entity.getUserName());
		response.setUserType(entity.getUserType());
		response.setScore(entity.getScore());
		response.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(entity.getCreatedOn()));
		
		return response;
	}

}
