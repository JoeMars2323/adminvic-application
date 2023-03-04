package com.marsoft.adminvic.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "film")
public class Film extends AbstractEntity {

	@Id
	private Long id;

	@Field("recomendedAgeId")
	private Integer recomendedAgeId;

	@Field("fimName")
	private String filmName;

	@Field("filmDescription")
	private String filmDescription;

	@Field("filmYear")
	private Integer filmYear;

	private List<Integer> categoryList;

	private List<Integer> actorList;

	private List<Integer> awardsList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRecomendedAgeId() {
		return recomendedAgeId;
	}

	public void setRecomendedAgeId(Integer recomendedAgeId) {
		this.recomendedAgeId = recomendedAgeId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getFilmDescription() {
		return filmDescription;
	}

	public void setFilmDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}

	public Integer getFilmYear() {
		return filmYear;
	}

	public void setFilmYear(Integer filmYear) {
		this.filmYear = filmYear;
	}

	public List<Integer> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Integer> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Integer> getActorList() {
		return actorList;
	}

	public void setActorList(List<Integer> actorList) {
		this.actorList = actorList;
	}

	public List<Integer> getAwardsList() {
		return awardsList;
	}

	public void setAwardsList(List<Integer> awardsList) {
		this.awardsList = awardsList;
	}

}
