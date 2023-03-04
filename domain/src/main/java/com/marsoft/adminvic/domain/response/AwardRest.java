package com.marsoft.adminvic.domain.response;

import java.io.Serializable;
import java.util.List;

public class AwardRest extends AbstractRest implements Serializable {

	private static final long serialVersionUID = 3006007809961963922L;

	private Long id;

	private String awardName;

	private String awardDescription;

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
