package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "recommendedAge")
public class RecommendedAgeSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "recommendedAge", type = "string")
	private String recommendedAge;

	@Indexed(name = "recommendedAgeDescription", type = "string")
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
