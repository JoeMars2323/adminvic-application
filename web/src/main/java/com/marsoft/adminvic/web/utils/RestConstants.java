package com.marsoft.adminvic.web.utils;

public class RestConstants {

	public static final String ACTOR = "/actor";
	public static final String ACTORS = "/actors";
	public static final String ACTOR_ID = "/actor/{id}";
	public static final String ACTOR_NAME = "/name/{name}";

	public static final String AWARD = "/award";
	public static final String AWARDS = "/awards";
	public static final String AWARD_ID = "/award/{id}";

	public static final String CATEGORY = "/category";
	public static final String CATEGORIES = "/categories";
	public static final String CATEGORY_ID = "/category/{id}";

	public static final String CHAPTER = "/chapter";
	public static final String CHAPTER_ID = "/chapter/{id}";
	public static final String CHAPTERS = "/chapters";

	public static final String FILM = "/film";
	public static final String FILM_ID = "/film/{id}";
	public static final String FILMS = "/films";

	public static final String PROFILE = "/profile";
	public static final String PROFILE_ID = "/profile/{id}";
	public static final String PROFILES = "/profiles";

	public static final String RECOMMENDATION = "/recommendation";
	public static final String RECOMMENDATION_ID = "/recommendation/{id}";
	public static final String RECOMMENDATIONS = "/recommendations";

	public static final String RECOMMENDED_AGE = "/recommended-age";
	public static final String RECOMMENDED_AGE_ID = "/recommended-age/{id}";
	public static final String RECOMMENDED_AGES = "/recommended-ages";

	public static final String SEASON = "/season";
	public static final String SEASON_ID = "/season/{id}";
	public static final String SEASONS = "/seasons";

	public static final String SUBSCRIPTION = "/subscription";
	public static final String SUBSCRIPTION_ID = "/subscription/{id}";
	public static final String SUBSCRIPTIONS = "/subscriptions";

	public static final String TV_SERIE = "/tvserie";
	public static final String TV_SERIE_ID = "/tvserie/{id}";
	public static final String TV_SERIES = "/tvseries";

	public static final String VISUALIZATION = "/visualization";
	public static final String VISUALIZATION_ID = "/visualization/{id}";
	public static final String VISUALIZATIONS = "/visualizations";

	public static final String SUCCESS = "Success";
	public static final String OK = "OK";

	public static final String FRONTEND_URL = "http://localhost:4200/actors";

	private RestConstants() {
		throw new IllegalStateException("Utility class");
	}

}
