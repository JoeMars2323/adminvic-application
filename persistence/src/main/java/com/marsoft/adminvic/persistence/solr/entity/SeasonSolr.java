package com.marsoft.adminvic.persistence.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "season")
public class SeasonSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private Integer tvserieId;
	@Field
	private String seasonName;
	@Field
	private Integer seasonNumber;
	@Field
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
