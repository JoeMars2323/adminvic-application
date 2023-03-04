package com.marsoft.adminvic.domain.response;

import java.io.Serializable;
import java.util.List;

public class TvserieRest extends AbstractRest implements Serializable {

	private static final long serialVersionUID = 1625260892375440478L;

	private Long id;

	private Integer recomendedAgeId;

	private String tvserieName;

	private String tvserieDescription;

	private List<Integer> categoryList;

	private List<Integer> actorStarringList;

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

	public String getTvserieName() {
		return tvserieName;
	}

	public void setTvserieName(String tvserieName) {
		this.tvserieName = tvserieName;
	}

	public String getTvserieDescription() {
		return tvserieDescription;
	}

	public void setTvserieDescription(String tvserieDescription) {
		this.tvserieDescription = tvserieDescription;
	}

	public List<Integer> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Integer> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Integer> getActorStarringList() {
		return actorStarringList;
	}

	public void setActorStarringList(List<Integer> actorStarringList) {
		this.actorStarringList = actorStarringList;
	}

	public List<Integer> getAwardsList() {
		return awardsList;
	}

	public void setAwardsList(List<Integer> awardsList) {
		this.awardsList = awardsList;
	}

}
