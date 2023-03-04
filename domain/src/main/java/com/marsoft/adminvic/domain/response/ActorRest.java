package com.marsoft.adminvic.domain.response;

import java.io.Serializable;
import java.util.List;

public class ActorRest extends AbstractRest implements Serializable {

	private static final long serialVersionUID = -3728548031236915642L;

	private Long id;

	private String actorName;

	private String actorBirthName;

	private String actorNickname;

	private String actorCountry;

	private String actorCity;

	private String actorBirthDate;

	private String actorHeight;

	private String actorBiography;

	private List<Integer> filmIdsList;

	private List<Integer> tvserieIdsList;

	private List<Integer> awardIdsList;

	private List<FilmRest> films;

	private List<TvserieRest> tvseries;

	private List<AwardRest> awards;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorBirthName() {
		return actorBirthName;
	}

	public void setActorBirthName(String actorBirthName) {
		this.actorBirthName = actorBirthName;
	}

	public String getActorNickname() {
		return actorNickname;
	}

	public void setActorNickname(String actorNickname) {
		this.actorNickname = actorNickname;
	}

	public String getActorCountry() {
		return actorCountry;
	}

	public void setActorCountry(String actorCountry) {
		this.actorCountry = actorCountry;
	}

	public String getActorCity() {
		return actorCity;
	}

	public void setActorCity(String actorCity) {
		this.actorCity = actorCity;
	}

	public String getActorBirthDate() {
		return actorBirthDate;
	}

	public void setActorBirthDate(String actorBirthDate) {
		this.actorBirthDate = actorBirthDate;
	}

	public String getActorHeight() {
		return actorHeight;
	}

	public void setActorHeight(String actorHeight) {
		this.actorHeight = actorHeight;
	}

	public String getActorBiography() {
		return actorBiography;
	}

	public void setActorBiography(String actorBiography) {
		this.actorBiography = actorBiography;
	}

	public List<FilmRest> getFilmsList() {
		return films;
	}

	public void setFilmsList(List<FilmRest> filmsList) {
		this.films = filmsList;
	}

	public List<TvserieRest> getTvseriesList() {
		return tvseries;
	}

	public void setTvseriesList(List<TvserieRest> tvseriesList) {
		this.tvseries = tvseriesList;
	}

	public List<AwardRest> getAwardsList() {
		return awards;
	}

	public void setAwardsList(List<AwardRest> awardsList) {
		this.awards = awardsList;
	}

	public List<Integer> getFilmIdsList() {
		return filmIdsList;
	}

	public void setFilmIdsList(List<Integer> filmIdsList) {
		this.filmIdsList = filmIdsList;
	}

	public List<Integer> getTvserieIdsList() {
		return tvserieIdsList;
	}

	public void setTvserieIdsList(List<Integer> tvserieIdsList) {
		this.tvserieIdsList = tvserieIdsList;
	}

	public List<Integer> getAwardIdsList() {
		return awardIdsList;
	}

	public void setAwardIdsList(List<Integer> awardIdsList) {
		this.awardIdsList = awardIdsList;
	}

}