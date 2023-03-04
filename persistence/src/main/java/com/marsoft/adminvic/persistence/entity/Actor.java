package com.marsoft.adminvic.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "actor")
public class Actor extends AbstractEntity {

	@Id
	private Long id;

	@Field("actorName")
	private String actorName;

	@Field("actorBirthName")
	private String actorBirthName;

	@Field("actorNickname")
	private String actorNickname;

	@Field("actorCountry")
	private String actorCountry;

	@Field("actorCity")
	private String actorCity;

	@Field("actorBirthDate")
	private String actorBirthDate;

	@Field("actorHeight")
	private String actorHeight;

	@Field("actorBiography")
	private String actorBiography;

	private List<Integer> filmIdsList;

	private List<Integer> tvserieIdsList;

	private List<Integer> awardIdsList;

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
