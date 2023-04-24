package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "actors")
public class ActorSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "name", type = "string")
	private String name;

	@Indexed(name = "actorBirthName", type = "string")
	private String actorBirthName;

	@Indexed(name = "actorNickname", type = "string")
	private String actorNickname;

	@Indexed(name = "actorCountry", type = "string")
	private String actorCountry;

	@Indexed(name = "actorCity", type = "string")
	private String actorCity;

	@Indexed(name = "actorBirthDate", type = "string")
	private String actorBirthDate;

	@Indexed(name = "actorHeight", type = "string")
	private String actorHeight;

	@Indexed(name = "actorBiography", type = "string")
	private String actorBiography;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActorName() {
		return name;
	}

	public void setActorName(String actorName) {
		this.name = actorName;
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
