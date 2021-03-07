package com.crejo.movie.review.constants;

public class MovieReviewConstants {

	private final String userTypeViewer = "Viewer";

	private final String userTypeCritic = "Critic";

	private final String userTypeExpert = "Expert";

	private final String userTypeAdmin = "Admin";

	private final Integer viewerRatingWeight = 1;

	private final Integer criticRatingWeight = 2;

	private final Integer expertRatingWeight = 2;

	private final Integer adminRatingWeight = 2;

	private final Integer viewerRatingCountThreshold = 3;

	private final Integer criticRatingCountThreshold = 10;

	private final Integer expertRatingCountThreshold = 10;

	private final Integer minMovieScore = 1;

	private final Integer maxMovieScore = 10;

	public String getUserTypeViewer() {
		return userTypeViewer;
	}

	public String getUserTypeCritic() {
		return userTypeCritic;
	}

	public String getUserTypeExpert() {
		return userTypeExpert;
	}

	public String getUserTypeAdmin() {
		return userTypeAdmin;
	}

	public Integer getViewerRatingWeight() {
		return viewerRatingWeight;
	}

	public Integer getCriticRatingWeight() {
		return criticRatingWeight;
	}

	public Integer getExpertRatingWeight() {
		return expertRatingWeight;
	}

	public Integer getAdminRatingWeight() {
		return adminRatingWeight;
	}

	public Integer getViewerRatingCountThreshold() {
		return viewerRatingCountThreshold;
	}

	public Integer getCriticRatingCountThreshold() {
		return criticRatingCountThreshold;
	}

	public Integer getExpertRatingCountThreshold() {
		return expertRatingCountThreshold;
	}

	public Integer getMinMovieScore() {
		return minMovieScore;
	}

	public Integer getMaxMovieScore() {
		return maxMovieScore;
	}

}
