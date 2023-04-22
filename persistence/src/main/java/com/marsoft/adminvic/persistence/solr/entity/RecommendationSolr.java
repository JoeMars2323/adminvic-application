package com.marsoft.adminvic.persistence.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "recomendation")
public class RecommendationSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private Integer visualizationId;
	@Field
	private Integer categoryId;
	@Field
	private Integer recomendationId;
	@Field
	private Integer percentageMatch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVisualizationId() {
		return visualizationId;
	}

	public void setVisualizationId(Integer visualizationId) {
		this.visualizationId = visualizationId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getRecomendationId() {
		return recomendationId;
	}

	public void setRecomendationId(Integer recomendationId) {
		this.recomendationId = recomendationId;
	}

	public Integer getPercentageMatch() {
		return percentageMatch;
	}

	public void setPercentageMatch(Integer percentageMatch) {
		this.percentageMatch = percentageMatch;
	}

}
