package com.marsoft.adminvic.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "recommended_age")
public class RecommendedAge extends AbstractEntity {

	@Id
	private Long id;

	@Field("recommendedAge")
	private String recommendedAge;

	@Field("recommendedAgeDescription")
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