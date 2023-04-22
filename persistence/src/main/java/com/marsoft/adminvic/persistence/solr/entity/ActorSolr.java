package com.marsoft.adminvic.persistence.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "actor")
public class ActorSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private String actorName;
	@Field
	private String actorBirthName;
	@Field
	private String actorNickname;
	@Field
	private String actorCountry;
	@Field
	private String actorCity;
	@Field
	private String actorBirthDate;
	@Field
	private String actorHeight;
	@Field
	private String actorBiography;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorBirthName() {
		return actorBirthName;
	}

	public void setActorBirthName(String actorBirthName) {
		this.actorBirthName = actorBirthName;
	}

	public String getActorNickname() {
		return actorNickname;
	}

	public void setActorNickname(String actorNickname) {
		this.actorNickname = actorNickname;
	}

	public String getActorCountry() {
		return actorCountry;
	}

	public void setActorCountry(String actorCountry) {
		this.actorCountry = actorCountry;
	}

	public String getActorCity() {
		return actorCity;
	}

	public void setActorCity(String actorCity) {
		this.actorCity = actorCity;
	}

	public String getActorBirthDate() {
		return actorBirthDate;
	}

	public void setActorBirthDate(String actorBirthDate) {
		this.actorBirthDate = actorBirthDate;
	}

	public String getActorHeight() {
		return actorHeight;
	}

	public void setActorHeight(String actorHeight) {
		this.actorHeight = actorHeight;
	}

	public String getActorBiography() {
		return actorBiography;
	}

	public void setActorBiography(String actorBiography) {
		this.actorBiography = actorBiography;
	}

}
