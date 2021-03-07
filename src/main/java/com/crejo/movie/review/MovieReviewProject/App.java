package com.crejo.movie.review.MovieReviewProject;

import java.util.ArrayList;
import java.util.List;

import com.crejo.movie.review.interfaces.IMovieReviewManagerService;
import com.crejo.movie.review.model.MovieResponse;
import com.crejo.movie.review.service.MovieReviewManagerService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		IMovieReviewManagerService movieReviewManagerService = new MovieReviewManagerService();

		try {
			movieReviewManagerService.addUser("SRK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addUser("Salman");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addUser("Deepika");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<String> genreList = new ArrayList<>();
		genreList.add("Horror");
		genreList.add("Torture");
		try {
			movieReviewManagerService.addMovie("Don", genreList, "06/03/2021");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovie("Padmavat", genreList, "06/03/2021");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("SRK", "Don", 7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			movieReviewManagerService.addMovieReview("SRK", "Padmavat", 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("Deepika", "Padmavat", 9);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			movieReviewManagerService.addMovieReview("Salman", "Padmavat", 3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			movieReviewManagerService.addMovieReview("Deepika", "Don", 8);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<MovieResponse> response = movieReviewManagerService.getTopNMoviesByCriteria(2, "Viewer", 2021, "Horror",
				false);
		System.out.println(response);

		Double avgScore = movieReviewManagerService.getAverageMovieReviewScoreByCriteria("Don", 2021, null);
		System.out.println(avgScore);

	}
}
