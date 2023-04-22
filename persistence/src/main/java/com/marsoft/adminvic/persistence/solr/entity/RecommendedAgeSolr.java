package com.marsoft.adminvic.persistence.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "recommended_age")
public class RecommendedAgeSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private String recommendedAge;
	@Field
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