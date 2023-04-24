package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "tvserie")
public class TvserieSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "recomendedAgeId", type = "int")
	private Integer recomendedAgeId;

	@Indexed(name = "tvserieName", type = "string")
	private String tvserieName;

	@Indexed(name = "tvserieDescription", type = "string")
	private String tvserieDescription;

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

}
