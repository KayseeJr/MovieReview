package com.crejo.movie.review.interfaces;

import java.util.Map;

import com.crejo.movie.review.model.UserEntity;


public interface IUserService {

	public Boolean addUser(String userName) throws Exception;

	public Map<String, UserEntity> getUserMap();

}
