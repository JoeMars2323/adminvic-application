package com.marsoft.adminvic.domain.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.exception.NotFoundException;
import com.marsoft.adminvic.persistence.entity.Visualization;
import com.marsoft.adminvic.persistence.repository.VisualizationRepository;
import com.marsoft.adminvic.persistence.solr.entity.VisualizationSolr;

@Service
public class VisualizationServiceImpl implements VisualizationService {

	private Logger log = LoggerFactory.getLogger(VisualizationServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String VISUALIZARION_NOT_FOUND = "Visualization not found!";
	private static final String VISUALIZATIONS_NOT_FOUND = "Visualizations not found!";

	@Autowired
	private VisualizationRepository visualizationRepository;

	@Override
	public VisualizationSolr getVisualizationById(Long id) throws AdminVicException {
		log.info("Geting visualization...");
		VisualizationSolr visualizationResponse = null;
		try {
			visualizationResponse = modelMapper.map(visualizationRepository.findById(id).orElse(null),
					VisualizationSolr.class);
			if (visualizationResponse != null) {
				log.info("Visualization found");
			} else {
				throw new NotFoundException(VISUALIZARION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return visualizationResponse;
	}

	@Override
	public List<VisualizationSolr> getAllVisualizations() throws AdminVicException {
		log.info("Geting all available visualizations...");
		List<VisualizationSolr> visualizationsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`visualization` USING GSI;
			 */
			visualizationsResponseList = visualizationRepository.findAll().stream()
					.map(visualization -> modelMapper.map(visualization, VisualizationSolr.class))
					.collect(Collectors.toList());
			if (!visualizationsResponseList.isEmpty()) {
				log.info("Visualizations found");
			} else {
				throw new NotFoundException(VISUALIZATIONS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return visualizationsResponseList;
	}

	@Override
	@Transactional
	public VisualizationSolr createVisualization(VisualizationSolr visualizationRest) throws AdminVicException {
		log.info("Creating visualization...");
		VisualizationSolr visualizationResponse = null;
		try {
			Visualization visualization = modelMapper.map(visualizationRest, Visualization.class);
			visualization.setInsertDate(String.valueOf(new Date()));
			visualizationResponse = modelMapper.map(visualizationRepository.save(visualization),
					VisualizationSolr.class);
			log.info("Visualization created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return visualizationResponse;
	}

	@Override
	@Transactional
	public VisualizationSolr updateVisualization(VisualizationSolr visualizationRest) throws AdminVicException {
		log.info("Updating visualization...");
		VisualizationSolr visualizationResponse = modelMapper
				.map(visualizationRepository.findById(visualizationRest.getId()).orElse(null), VisualizationSolr.class);
		if (visualizationResponse != null) {
			try {
				Visualization visualization = modelMapper.map(visualizationRest, Visualization.class);
				visualization.setUpdatedDate(String.valueOf(new Date()));
				visualization = visualizationRepository.save(visualization);
				visualizationResponse = modelMapper.map(visualization, VisualizationSolr.class);
				log.info("Visualization updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(VISUALIZARION_NOT_FOUND);
		}
		return visualizationResponse;
	}

	@Override
	@Transactional
	public VisualizationSolr deleteVisualization(Long id) throws AdminVicException {
		log.info("Deliting visualization...");
		VisualizationSolr visualizationResponse = null;
		try {
			Visualization visualization = visualizationRepository.findById(id).orElse(null);
			if (visualization != null) {
				visualization.setDeleted(true);
				visualization = visualizationRepository.save(visualization);
				visualizationResponse = modelMapper.map(visualization, VisualizationSolr.class);
				log.info("Visualization deleted");
			} else {
				throw new NotFoundException(VISUALIZARION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return visualizationResponse;
	}

	@Override
	@Transactional
	public VisualizationSolr deleteVisualizationPhysically(Long id) throws AdminVicException {
		log.info("Deliting visualization physically...");
		VisualizationSolr visualizationResponse = null;
		try {
			Visualization visualization = visualizationRepository.findById(id).orElse(null);
			if (visualization != null) {
				visualizationRepository.delete(visualization);
				visualizationResponse = modelMapper.map(visualization, VisualizationSolr.class);
				log.info("Visualization deleted physically");
			} else {
				throw new NotFoundException(VISUALIZARION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return visualizationResponse;
	}

}
