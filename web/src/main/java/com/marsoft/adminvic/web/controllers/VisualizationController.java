package com.marsoft.adminvic.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.AdminVicResponse;
import com.marsoft.adminvic.domain.response.VisualizationRest;
import com.marsoft.adminvic.domain.service.VisualizationService;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.VISUALIZATIONS)
public class VisualizationController {

	@Autowired
	private VisualizationService visualizationService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.VISUALIZATION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<VisualizationRest> getVisualizationsById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				visualizationService.getVisualizationById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<VisualizationRest>> getAllVisualizations() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				visualizationService.getAllVisualizations());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<VisualizationRest> createVisualization(@RequestBody VisualizationRest visualizationRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				visualizationService.createVisualization(visualizationRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<VisualizationRest> updateVisualization(@RequestBody VisualizationRest visualizationRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				visualizationService.updateVisualization(visualizationRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.VISUALIZATION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<VisualizationRest> deleteVisualization(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				visualizationService.deleteVisualization(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.VISUALIZATION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<VisualizationRest> deleteVisualizationPhysically(@PathVariable Long id)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				visualizationService.deleteVisualizationPhysically(id));
	}

}
