package com.crejo.movie.review.interfaces;

import java.util.List;

import com.crejo.movie.review.model.MovieEntity;
import com.crejo.movie.review.model.ReviewResponse;
import com.crejo.movie.review.model.UserEntity;

public interface IReviewService {

	public Boolean addMovieReview(String userName, String movieName, Integer rating, UserEntity userEntity,
			MovieEntity movieEntity) throws Exception;

	public List<ReviewResponse> getAllReviews();

}
