package com.crejo.movie.review.mappers;

import java.text.SimpleDateFormat;

import com.crejo.movie.review.model.UserEntity;
import com.crejo.movie.review.model.UserResponse;

public class UserMapper {

	public static UserResponse mapEntityToResponse(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.setName(entity.getName());
		response.setRatingCount(entity.getRatingCount());
		response.setType(entity.getType());
		response.setCreatedOn(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(entity.getCreatedOn()));
		return response;
	}

}
