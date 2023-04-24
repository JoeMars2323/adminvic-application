package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "award")
public class AwardSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "awardName", type = "string")
	private String awardName;

	@Indexed(name = "awardDescription", type = "string")
	private String awardDescription;

	@Indexed(name = "awardYear", type = "int")
	private Integer awardYear;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardDescription() {
		return awardDescription;
	}

	public void setAwardDescription(String awardDescription) {
		this.awardDescription = awardDescription;
	}

	public Integer getAwardYear() {
		return awardYear;
	}

	public void setAwardYear(Integer awardYear) {
		this.awardYear = awardYear;
	}

}
