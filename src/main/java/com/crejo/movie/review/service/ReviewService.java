package com.crejo.movie.review.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crejo.movie.review.constants.MovieReviewConstants;
import com.crejo.movie.review.interfaces.IReviewService;
import com.crejo.movie.review.mappers.ReviewMapper;
import com.crejo.movie.review.model.MovieEntity;
import com.crejo.movie.review.model.ReviewEntity;
import com.crejo.movie.review.model.ReviewResponse;
import com.crejo.movie.review.model.UserEntity;

public class ReviewService implements IReviewService {

	private MovieReviewConstants movieReviewConstants = new MovieReviewConstants();

	public Map<Long, ReviewEntity> reviewMap = new HashMap<>();

	public Map<String, List<String>> userMovieReviewMap = new HashMap<>();

	public Map<String, List<Integer>> movieReviewIdMap = new HashMap<>();

	public Long reviewCount = 0l;

	@Override
	public Boolean addMovieReview(String userName, String movieName, Integer rating, UserEntity userEntity,
			MovieEntity movieEntity) throws Exception {

		if (userMovieReviewMap.get(userName) != null && !userMovieReviewMap.get(userName).isEmpty()
				&& userMovieReviewMap.get(userName).contains(movieName)) {
			throw new Exception("Multiple reviews not allowed");
		}

		String userType = userEntity.getType();
		rating *= getRatingWeightageByUserType(userType);

		reviewCount++;
		ReviewEntity entity = new ReviewEntity(reviewCount, userName, movieName, userType, rating);
		reviewMap.put(reviewCount, entity);

		userEntity.setRatingCount(userEntity.getRatingCount() + 1);
		userEntity.setType(getUserTypeByUserRatingCountunt(userEntity.getRatingCount()));

		List<String> movieList = userMovieReviewMap.get(userName);
		if (movieList == null || movieList.isEmpty()) {
			movieList = new ArrayList<>();
			userMovieReviewMap.put(userName, movieList);
		}
		movieList.add(movieName);

		return true;

	}

	@Override
	public List<ReviewResponse> getAllReviews() {
		List<ReviewResponse> responseList = new ArrayList<>();
		List<ReviewEntity> entityList = new ArrayList<>(reviewMap.values());
		for (ReviewEntity entity : entityList) {
			responseList.add(ReviewMapper.mapEntityToResponse(entity));
		}
		return responseList;
	}

	private Integer getRatingWeightageByUserType(String userType) {
		Integer ratingWeightage = movieReviewConstants.getViewerRatingWeight();
		if (userType.equals(movieReviewConstants.getUserTypeViewer())) {
			ratingWeightage = movieReviewConstants.getViewerRatingWeight();
		} else if (userType.equals(movieReviewConstants.getUserTypeCritic())) {
			ratingWeightage = movieReviewConstants.getCriticRatingWeight();
		} else if (userType.equals(movieReviewConstants.getUserTypeExpert())) {
			ratingWeightage = movieReviewConstants.getExpertRatingWeight();
		} else if (userType.equals(movieReviewConstants.getUserTypeAdmin())) {
			ratingWeightage = movieReviewConstants.getAdminRatingWeight();
		}
		return ratingWeightage;
	}

	private String getUserTypeByUserRatingCountunt(Long ratingCount) {
		String userType = movieReviewConstants.getUserTypeViewer();
		if (ratingCount < movieReviewConstants.getViewerRatingCountThreshold()) {
			userType = movieReviewConstants.getUserTypeViewer();
		} else if (ratingCount < movieReviewConstants.getCriticRatingCountThreshold()) {
			userType = movieReviewConstants.getUserTypeCritic();
		} else if (ratingCount < movieReviewConstants.getExpertRatingCountThreshold()) {
			userType = movieReviewConstants.getUserTypeExpert();
		} else {
			userType = movieReviewConstants.getUserTypeAdmin();
		}
		return userType;
	}

}
