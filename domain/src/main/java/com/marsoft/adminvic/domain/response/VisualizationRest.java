package com.marsoft.adminvic.domain.response;

public class VisualizationRest extends AbstractRest {

	private Long id;

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
