package com.crejo.movie.review.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.crejo.movie.review.constants.MovieReviewConstants;
import com.crejo.movie.review.interfaces.IMovieReviewManagerService;
import com.crejo.movie.review.interfaces.IMovieService;
import com.crejo.movie.review.interfaces.IReviewService;
import com.crejo.movie.review.interfaces.IUserService;
import com.crejo.movie.review.mappers.MovieMapper;
import com.crejo.movie.review.model.MovieEntity;
import com.crejo.movie.review.model.MovieResponse;
import com.crejo.movie.review.model.ReviewResponse;
import com.crejo.movie.review.model.UserEntity;

public class MovieReviewManagerService implements IMovieReviewManagerService {

	private MovieReviewConstants movieReviewConstants = new MovieReviewConstants();

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
		if (userName == null || userName.isEmpty()) {
			throw new Exception("Please enter a valid username");
		}
		if (userService.getUserMap().containsKey(userName)) {
			throw new Exception("Username already exist");
		}
		return userService.addUser(userName);
	}

	@Override
	public Boolean addMovie(String movieName, List<String> genreList, String releaseDate) throws Exception {
		if (movieName == null || movieName.isEmpty()) {
			throw new Exception("Movie already exists");
		}
		if (genreList == null || genreList.isEmpty()) {
			throw new Exception("Movie genre can't be empty");
		}
		Date movieDate;
		try {
			movieDate = new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);
		} catch (ParseException e) {
			throw new Exception("Invalid release year format.");
		}

		if (movieService.getMovieMap().containsKey(movieName)) {
			throw new Exception("Movie already exists");
		}

		return movieService.addMovie(movieName, genreList, movieDate);

	}

	@Override
	public Boolean addMovieReview(String userName, String movieName, Integer rating) throws Exception {
		UserEntity userEntity = userService.getUserMap().get(userName);
		if (userName == null || userName.isEmpty()) {
			throw new Exception("Please enter a valid username");
		}
		if (userEntity == null) {
			throw new Exception("User doesn't exist");
		}
		MovieEntity movieEntity = movieService.getMovieMap().get(movieName);
		if (movieName == null || movieName.isEmpty()) {
			throw new Exception("Please enter a valid movie name");
		}
		if (movieEntity == null) {
			throw new Exception("Movie doesn't exist");
		}
		if (rating < movieReviewConstants.getMinMovieScore() || rating > movieReviewConstants.getMaxMovieScore()) {
			throw new Exception("Please enter a rating between " + movieReviewConstants.getMinMovieScore() + " to "
					+ movieReviewConstants.getMaxMovieScore());
		}
		if (new Date().before(movieEntity.getReleaseDate())) {
			throw new Exception("Movie yet to be released");
		}
		return reviewService.addMovieReview(userName, movieName, rating, userEntity, movieEntity);
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
			Collections.sort(movieList, new Comparator<MovieResponse>() {
				@Override
				public int compare(MovieResponse m1, MovieResponse m2) {
					return m1.getAvgScore().compareTo(m2.getAvgScore()) > 0 ? -1 : 1;
				}
			});
		} else {
			Collections.sort(movieList, new Comparator<MovieResponse>() {
				@Override
				public int compare(MovieResponse m1, MovieResponse m2) {
					return m1.getTotalScore().compareTo(m2.getTotalScore()) > 0 ? -1 : 1;
				}
			});
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
		if (reviewList.isEmpty()) {
			return avgReviewScore;
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
