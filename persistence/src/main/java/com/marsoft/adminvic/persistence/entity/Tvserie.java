package com.marsoft.adminvic.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "tv_serie")
public class Tvserie extends AbstractEntity {

	@Id
	private Long id;

	@Field("recomendedAgeId")
	private Integer recomendedAgeId;

	@Field("tvserieName")
	private String tvserieName;

	@Field("tvserieDescription")
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
