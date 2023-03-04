package com.marsoft.adminvic.domain.response;

import java.io.Serializable;
import java.util.List;

public class FilmRest extends AbstractRest implements Serializable {

	private static final long serialVersionUID = -3657084635025289016L;

	private Long id;

	private Integer recomendedAgeId;

	private String filmName;

	private String filmDescription;

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

	public void setTvserieDescription(String filmDescription) {
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
