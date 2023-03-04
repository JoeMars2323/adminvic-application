package com.marsoft.adminvic.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "season")
public class Season extends AbstractEntity {

	@Id
	private Long id;

	@Field("tvserieId")
	private Integer tvserieId;

	@Field("seasonName")
	private String seasonName;

	@Field("seasonNumber")
	private Integer seasonNumber;

	@Field("seasonId")
	private Integer seasonYear;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTvserieId() {
		return tvserieId;
	}

	public void setTvserieId(Integer tvserieId) {
		this.tvserieId = tvserieId;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public Integer getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public Integer getSeasonYear() {
		return seasonYear;
	}

	public void setSeasonYear(Integer seasonYear) {
		this.seasonYear = seasonYear;
	}

}
