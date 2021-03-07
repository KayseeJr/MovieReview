package com.crejo.movie.review.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.crejo.movie.review.interfaces.IMovieReviewManagerService;
import com.crejo.movie.review.interfaces.IMovieService;
import com.crejo.movie.review.interfaces.IReviewService;
import com.crejo.movie.review.interfaces.IUserService;
import com.crejo.movie.review.mappers.MovieMapper;
import com.crejo.movie.review.model.MovieEntity;
import com.crejo.movie.review.model.MovieResponse;
import com.crejo.movie.review.model.ReviewResponse;

public class MovieReviewManagerService implements IMovieReviewManagerService {

	private IUserService userService;
	private IMovieService movieService;
	private IReviewService reviewService;

	public MovieReviewManagerService() {
		this.userService = new UserService();
		this.movieService = new MovieService();
		this.reviewService = new ReviewService();
	}

	@Override
	public Boolean addUser(String userName) throws Exception {
		return userService.addUser(userName);
	}

	@Override
	public Boolean addMovie(String movieName, List<String> genreList, String releaseDate) throws Exception {
		return movieService.addMovie(movieName, genreList, releaseDate);

	}

	@Override
	public Boolean addMovieReview(String userName, String movieName, Integer rating) throws Exception {
		return reviewService.addMovieReview(userName, movieName, rating, userService.getUserMap(),
				movieService.getMovieMap());

	}

	@Override
	public List<MovieResponse> getTopNMoviesByCriteria(Integer n, String userType, Integer releaseYear, String genre,
			Boolean isAvg) {
		List<MovieResponse> movieList = new ArrayList<>();
		List<ReviewResponse> reviewList = reviewService.getAllReviews();
		if (reviewList.isEmpty()) {
			return movieList;
		}
		Map<String, MovieEntity> movieMap = movieService.getMovieMap();

		Iterator<ReviewResponse> i = reviewList.iterator();
		while (i.hasNext()) {
			ReviewResponse reviewResponse = i.next();
			if (userType != null && !reviewResponse.getUserType().equals(userType)) {
				i.remove();
				continue;
			}
			if (genre != null && !movieMap.get(reviewResponse.getMovieName()).getGenreList().contains(genre)) {
				i.remove();
				continue;
			}
			if (releaseYear != null
					&& movieMap.get(reviewResponse.getMovieName()).getReleaseDate().getYear() != releaseYear - 1900) {
				i.remove();
				continue;
			}
		}

		Map<String, MovieResponse> movieNameMovieResponseMap = new HashMap<>();
		for (ReviewResponse response : reviewList) {
			MovieResponse movieResponse = movieNameMovieResponseMap.get(response.getMovieName());
			if (movieResponse == null) {
				movieResponse = MovieMapper.mapEntityToResponse(movieMap.get(response.getMovieName()));
				movieResponse.setTotalScore(response.getScore().longValue());
				movieResponse.setTotalRatings(1l);
				movieResponse.setAvgScore(response.getScore().doubleValue());
				movieNameMovieResponseMap.put(response.getMovieName(), movieResponse);
				movieList.add(movieResponse);
			} else {
				movieResponse.setTotalScore(movieResponse.getTotalScore() + response.getScore().longValue());
				movieResponse.setTotalRatings(movieResponse.getTotalRatings() + 1l);
				Double avgScore = movieResponse.getTotalScore().doubleValue()
						/ movieResponse.getTotalRatings().doubleValue();
				movieResponse.setAvgScore(avgScore);
			}
		}

		if (isAvg) {
			movieList.sort(
					Comparator.comparing(MovieResponse::getAvgScore, Comparator.nullsFirst(Comparator.naturalOrder())));
		} else {
			movieList.sort(Comparator.comparing(MovieResponse::getTotalScore,
					Comparator.nullsFirst(Comparator.naturalOrder())));
		}
		if (movieList.size() == 0) {
			return movieList;
		}

		Integer size = movieList.size();
		if (size < n) {
			n = size;
		}
		movieList = movieList.subList(0, n);
		return movieList;
	}

	@Override
	public Double getAverageMovieReviewScoreByCriteria(String movieName, Integer releaseYear, String genre) {
		Double avgReviewScore = 0d;
		List<ReviewResponse> reviewList = reviewService.getAllReviews();
		if (reviewList.isEmpty()) {
			return avgReviewScore;
		}
		Map<String, MovieEntity> movieMap = movieService.getMovieMap();
		Iterator<ReviewResponse> i = reviewList.iterator();
		while (i.hasNext()) {
			ReviewResponse reviewResponse = i.next();
			if (movieName != null && !reviewResponse.getMovieName().equals(movieName)) {
				i.remove();
				continue;
			}
			if (genre != null && !movieMap.get(reviewResponse.getMovieName()).getGenreList().contains(genre)) {
				i.remove();
				continue;
			}
			if (releaseYear != null
					&& movieMap.get(reviewResponse.getMovieName()).getReleaseDate().getYear() != releaseYear - 1900) {
				i.remove();
				continue;
			}
		}

		Long ratingcount = 0l;
		for (ReviewResponse response : reviewList) {
			avgReviewScore += response.getScore().doubleValue();
			ratingcount++;
		}
		avgReviewScore = avgReviewScore / ratingcount.doubleValue();

		return avgReviewScore;
	}

}
