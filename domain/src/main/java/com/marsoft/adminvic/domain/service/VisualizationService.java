package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.VisualizationRest;

public interface VisualizationService {

	VisualizationRest getVisualizationById(Long id) throws AdminVicException;

	List<VisualizationRest> getAllVisualizations() throws AdminVicException;

	VisualizationRest createVisualization(VisualizationRest visualizationRest) throws AdminVicException;

	VisualizationRest updateVisualization(VisualizationRest visualizationRest) throws AdminVicException;

	VisualizationRest deleteVisualization(Long id) throws AdminVicException;

	VisualizationRest deleteVisualizationPhysically(Long id) throws AdminVicException;

}
