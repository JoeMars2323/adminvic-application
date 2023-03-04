package com.marsoft.adminvic.domain.response;

public class RecommendedAgeRest extends AbstractRest {

	private Long id;

	private String recommendedAge;

	private String recommendedAgeDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(String recommendedAge) {
		this.recommendedAge = recommendedAge;
	}

	public String getRecommendedAgeDescription() {
		return recommendedAgeDescription;
	}

	public void setRecommendedAgeDescription(String recommendedAgeDescription) {
		this.recommendedAgeDescription = recommendedAgeDescription;
	}

}
