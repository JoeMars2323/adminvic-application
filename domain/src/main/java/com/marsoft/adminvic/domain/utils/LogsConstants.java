package com.marsoft.adminvic.domain.utils;

public class LogsConstants {

	// actors constants
	public static final String GETTING_ACTOR = "Geting actor...";
	public static final String ACTOR_FOUND = "Actor found!";
	public static final String ACTOR_NOT_FOUND = "Actor not found!";
	public static final String GETTING_ALL_ACTORS = "Geting all available actors...";
	public static final String ACTORS_FOUND = "Actors found!";
	public static final String ACTORS_NOT_FOUND = "Actors not found!";
	public static final String CREATING_ACTOR = "Creating actor...";
	public static final String ACTOR_CREATED_COUCHBASE = "Actor created in database!";
	public static final String ACTOR_CREATED_SOLR = "Actor created in search ingine!";
	public static final String ACTOR_NOT_CREATED = "Unable to create actor!";
	public static final String UPDATING_ACTOR = "Updating actor...";
	public static final String ACTOR_UPDATED = "Actor updated!";
	public static final String ACTOR_NOT_UPDATED = "Unable to update actor!";
	public static final String DELETING_ACTOR = "Deliting actor...";
	public static final String ACTOR_DELETED = "Actor deleted!";
	public static final String ACTOR_NOT_DELETED = "Unable to delete actor!";
	public static final String DELETING_ACTOR_PHISICALLY = "Deliting actor physically...";
	public static final String ACTOR_DELETED_PHISICALLY = "Actor deleted physically!";
	public static final String ACTOR_NOT_DELETED_PHISICALLY = "Unable to delete actor physically!";

	// films constants
	public static final String GETTING_FILM = "Geting film...";
	public static final String FILM_FOUND = "Film found!";
	public static final String FILM_NOT_FOUND = "Film not found!";
	public static final String GETTING_ALL_FILMS = "Geting all available films...";
	public static final String FILMS_FOUND = "Films found!";
	public static final String FILMS_NOT_FOUND = "Films not found!";
	public static final String CREATING_FILM = "Creating film...";
	public static final String FILM_CREATED = "Film created!";
	public static final String FILM_NOT_CREATED = "Unable to create film!";
	public static final String UPDATING_FILM = "Updating film...";
	public static final String FILM_UPDATED = "Film updated!";
	public static final String FILM_NOT_UPDATED = "Unable to update film!";
	public static final String DELETING_FILM = "Deliting film...";
	public static final String FILM_DELETED = "Film deleted!";
	public static final String FILM_NOT_DELETED = "Unable to delete film!";
	public static final String DELETING_FILM_PHISICALLY = "Deliting film physically...";
	public static final String FILM_DELETED_PHISICALLY = "Film deleted physically!";
	public static final String FILM_NOT_DELETED_PHISICALLY = "Unable to delete film physically!";

	// films constants
	public static final String GETTING_TVSERIE = "Geting tvserie...";
	public static final String TVSERIE_FOUND = "Tvserie found!";
	public static final String TVSERIE_NOT_FOUND = "Tvserie not found!";
	public static final String GETTING_ALL_TVSERIES = "Geting all available tv series.";
	public static final String TVSERIES_FOUND = "Tvserie found!";
	public static final String TVSERIES_NOT_FOUND = "Tvseries not found!";
	public static final String CREATING_TVSERIE = "Creating tvserie...";
	public static final String TVSERIE_CREATED = "Tvserie created!";
	public static final String TVSERIE_NOT_CREATED = "Unable to create tvserie!";
	public static final String UPDATING_TVSERIE = "Updating tvserie...";
	public static final String TVSERIE_UPDATED = "Tvserie updated!";
	public static final String TVSERIE_NOT_UPDATED = "Unable to update tvserie!";
	public static final String DELETING_TVSERIE = "Deliting tvserie...";
	public static final String TVSERIE_DELETED = "Tvserie deleted!";
	public static final String TVSERIE_NOT_DELETED = "Unable to delete tvserie!";
	public static final String DELETING_TVSERIE_PHISICALLY = "Deliting tvserie physically...";
	public static final String TVSERIE_DELETED_PHISICALLY = "Tvserie deleted physically!";
	public static final String TVSERIE_NOT_DELETED_PHISICALLY = "Unable to delete tvserie physically!";

	// tv series constants
	public static final String MESSAGE_INEXISTENT_SEASON = "SEASON INEXISTENT - Season does not exist";
	public static final String MESSAGE_INEXISTENT_CHAPTER = "CHAPTER INEXISTENT - Chapter does not exist";

	public static final String ERROR_MESSAGE = "ERROR: ";
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR - An internal server error has ocurred";

	private LogsConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
