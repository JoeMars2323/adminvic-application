package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "film")
public class FilmSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private Integer recomendedAgeId;
	@Field
	private String filmName;
	@Field
	private String filmDescription;
	@Field
	private Integer filmYear;

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

}
