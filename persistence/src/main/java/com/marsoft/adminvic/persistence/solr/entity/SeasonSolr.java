package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "season")
public class SeasonSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "tvserieId", type = "int")
	private Integer tvserieId;

	@Indexed(name = "seasonName", type = "string")
	private String seasonName;

	@Indexed(name = "seasonNumber", type = "int")
	private Integer seasonNumber;

	@Indexed(name = "seasonYear", type = "int")
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
