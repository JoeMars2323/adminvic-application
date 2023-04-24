package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.VisualizationSolr;

public interface VisualizationService {

	VisualizationSolr getVisualizationById(Long id) throws AdminVicException;

	List<VisualizationSolr> getAllVisualizations() throws AdminVicException;

	VisualizationSolr createVisualization(VisualizationSolr visualizationRest) throws AdminVicException;

	VisualizationSolr updateVisualization(VisualizationSolr visualizationRest) throws AdminVicException;

	VisualizationSolr deleteVisualization(Long id) throws AdminVicException;

	VisualizationSolr deleteVisualizationPhysically(Long id) throws AdminVicException;

}
