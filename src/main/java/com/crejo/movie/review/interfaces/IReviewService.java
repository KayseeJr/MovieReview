package com.crejo.movie.review.interfaces;

import java.util.List;
import java.util.Map;

import com.crejo.movie.review.model.MovieEntity;
import com.crejo.movie.review.model.ReviewResponse;
import com.crejo.movie.review.model.UserEntity;

public interface IReviewService {

	public Boolean addMovieReview(String userName, String movieName, Integer rating, Map<String, UserEntity> userMap,
			Map<String, MovieEntity> movieMap) throws Exception;

	public List<ReviewResponse> getAllReviews();

}
