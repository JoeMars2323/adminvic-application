package com.marsoft.adminvic.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Scope(value = "dev")
@Collection(value = "visualization")
public class Visualization extends AbstractEntity {

	@Id
	private Long id;

	@Field("visualizationId")
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
