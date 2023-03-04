package com.marsoft.adminvic.domain.response;

import java.util.List;

public class RecommendationRest extends AbstractRest {

	private Long id;

	private Integer visualizationId;

	private Integer categoryId;

	private Integer recomendationId;

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
