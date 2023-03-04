package com.marsoft.adminvic.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "recomendation")
public class Recommendation extends AbstractEntity {

	@Id
	private Long id;

	@Field("visualizationId")
	private Integer visualizationId;

	@Field("categoryId")
	private Integer categoryId;

	@Field("recomendationId")
	private Integer recomendationId;

	@Field("percentageMatch")
	private Integer percentageMatch;

	private List<Integer> profileList;

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

	public List<Integer> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Integer> profileList) {
		this.profileList = profileList;
	}

}
