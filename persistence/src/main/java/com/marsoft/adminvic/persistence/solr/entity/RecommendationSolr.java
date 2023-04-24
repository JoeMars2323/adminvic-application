package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "recomendation")
public class RecommendationSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "visualizationId", type = "int")
	private Integer visualizationId;

	@Indexed(name = "categoryId", type = "int")
	private Integer categoryId;

	@Indexed(name = "recomendationId", type = "int")
	private Integer recomendationId;

	@Indexed(name = "percentageMatch", type = "int")
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
