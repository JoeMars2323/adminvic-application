package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "subscription")
public class SubscriptionSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "profileId", type = "int")
	private Integer profileId;

	@Indexed(name = "subscriptionDate", type = "int")
	private String subscriptionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

}
