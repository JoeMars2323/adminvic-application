package com.marsoft.adminvic.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.marsoft.adminvic.domain.service.RecommendationService;
import com.marsoft.adminvic.persistence.solr.entity.RecommendationSolr;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.RECOMMENDATIONS)
@CrossOrigin(origins = RestConstants.FRONTEND_URL)
public class RecommendationController {

	@Autowired
	private RecommendationService recommendationService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RECOMMENDATION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendationSolr> getRecommendationsById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendationService.getRecommendationById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<RecommendationSolr>> getAllRecommendations() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendationService.getAllRecommendations());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendationSolr> createRecommendation(@RequestBody RecommendationSolr recommendationRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendationService.createRecommendation(recommendationRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendationSolr> updateRecommendation(@RequestBody RecommendationSolr recommendationRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendationService.updateRecommendation(recommendationRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RECOMMENDATION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendationSolr> deleteRecommendation(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendationService.deleteRecommendation(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.RECOMMENDATION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendationSolr> deleteRecommendationPhysically(@PathVariable Long id)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendationService.deleteRecommendationPhysically(id));
	}

}
