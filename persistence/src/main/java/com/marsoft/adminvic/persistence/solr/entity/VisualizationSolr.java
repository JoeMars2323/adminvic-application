package com.marsoft.adminvic.persistence.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "visualization")
public class VisualizationSolr extends AbstractSolrEntity {

	@Id
	@Field
	private Long id;
	@Field
	private String visualizationType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisualizationType() {
		return visualizationType;
	}

	public void setVisualizationType(String visualizationType) {
		this.visualizationType = visualizationType;
	}

}
