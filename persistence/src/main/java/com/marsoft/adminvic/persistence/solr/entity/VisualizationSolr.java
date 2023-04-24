package com.marsoft.adminvic.persistence.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "visualisation")
public class VisualizationSolr extends AbstractSolrEntity {

	@Id
	@Indexed(name = "id", type = "long")
	private Long id;

	@Indexed(name = "visualizationType", type = "string")
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
