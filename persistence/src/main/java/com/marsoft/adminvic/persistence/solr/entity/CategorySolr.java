package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "category")
public class CategorySolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "categoryName", type = "string")
	private String categoryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}