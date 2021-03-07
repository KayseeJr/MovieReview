package com.crejo.movie.review.MovieReviewProject;

import java.text.DecimalFormat;
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

		// Adding Movies
		List<String> donMoviegenreList = new ArrayList<>();
		donMoviegenreList.add("Action");
		donMoviegenreList.add("Comedy");
		try {
			movieReviewManagerService.addMovie("Don", donMoviegenreList, "06/06/2006");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<String> tigerMoviegenreList = new ArrayList<>();
		tigerMoviegenreList.add("Drama");
		try {
			movieReviewManagerService.addMovie("Tiger", tigerMoviegenreList, "08/08/2008");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<String> padmaavatMoviegenreList = new ArrayList<>();
		padmaavatMoviegenreList.add("Comedy");
		try {
			movieReviewManagerService.addMovie("Padmaavat", padmaavatMoviegenreList, "21/11/2006");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<String> lunchboxMoviegenreList = new ArrayList<>();
		lunchboxMoviegenreList.add("Drama");
		try {
			movieReviewManagerService.addMovie("Lunchbox", lunchboxMoviegenreList, "21/05/2021");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<String> guruMoviegenreList = new ArrayList<>();
		guruMoviegenreList.add("Drama");
		try {
			movieReviewManagerService.addMovie("Guru", guruMoviegenreList, "08/05/2006");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<String> metroMoviegenreList = new ArrayList<>();
		metroMoviegenreList.add("Romance");
		try {
			movieReviewManagerService.addMovie("Metro", metroMoviegenreList, "15/08/2006");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Adding Users
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

		// Adding Reviews
		try {
			movieReviewManagerService.addMovieReview("SRK", "Don", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("SRK", "Padmaavat", 8);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("Salman", "Don", 5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("Deepika", "Don", 9);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("Deepika", "Guru", 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("SRK", "Don", 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("Deepika", "Lunchbox", 5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("SRK", "Tiger", 5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			movieReviewManagerService.addMovieReview("SRK", "Metro", 7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<MovieResponse> response = movieReviewManagerService.getTopNMoviesByCriteria(1, null, 2006, null, false);
		System.out.println(response);

		response = movieReviewManagerService.getTopNMoviesByCriteria(1, "Critic", null, null, false);
		System.out.println(response);

		response = movieReviewManagerService.getTopNMoviesByCriteria(1, null, null, "Drama", false);
		System.out.println(response);

		DecimalFormat f = new DecimalFormat("##.00");
		Double avgScore = movieReviewManagerService.getAverageMovieReviewScoreByCriteria(null, 2006, null);
		System.out.println(f.format(avgScore));

	}
}
