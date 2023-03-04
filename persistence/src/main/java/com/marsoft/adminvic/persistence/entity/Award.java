package com.marsoft.adminvic.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "award")
public class Award extends AbstractEntity {

	@Id
	private Long id;

	@Field("awardName")
	private String awardName;

	@Field("awardDescription")
	private String awardDescription;

	@Field("awardYear")
	private Integer awardYear;

	private List<Integer> filmIdsList;

	private List<Integer> tvserieIdsList;

	private List<Integer> actorIdsList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardDescription() {
		return awardDescription;
	}

	public void setAwardDescription(String awardDescription) {
		this.awardDescription = awardDescription;
	}

	public Integer getAwardYear() {
		return awardYear;
	}

	public void setAwardYear(Integer awardYear) {
		this.awardYear = awardYear;
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

	public List<Integer> getActorIdsList() {
		return actorIdsList;
	}

	public void setActorIdsList(List<Integer> actorIdsList) {
		this.actorIdsList = actorIdsList;
	}

}
