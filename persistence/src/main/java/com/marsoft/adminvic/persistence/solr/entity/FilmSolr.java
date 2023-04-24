package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "film")
public class FilmSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "recomendedAgeId", type = "int")
	private Integer recomendedAgeId;

	@Indexed(name = "filmName", type = "string")
	private String filmName;

	@Indexed(name = "filmDescription", type = "string")
	private String filmDescription;

	@Indexed(name = "filmYear", type = "int")
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

	public void setTvserieDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}

	public Integer getFilmYear() {
		return filmYear;
	}

	public void setFilmYear(Integer filmYear) {
		this.filmYear = filmYear;
	}

}
