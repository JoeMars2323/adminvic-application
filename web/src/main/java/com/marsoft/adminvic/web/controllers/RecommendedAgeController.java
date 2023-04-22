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
import com.marsoft.adminvic.domain.response.RecommendedAgeRest;
import com.marsoft.adminvic.domain.service.RecommendedAgeService;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.RECOMMENDED_AGES)
@CrossOrigin(origins = RestConstants.FRONTEND_URL)
public class RecommendedAgeController {

	@Autowired
	private RecommendedAgeService recommendedAgeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RECOMMENDED_AGE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendedAgeRest> getRecommendedAgesById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendedAgeService.getRecommendedAgeById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<RecommendedAgeRest>> getAllRecommendedAges() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendedAgeService.getAllRecommendedAges());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendedAgeRest> createRecommendedAge(@RequestBody RecommendedAgeRest recommendedAgeRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendedAgeService.createRecommendedAge(recommendedAgeRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendedAgeRest> updateRecommendedAge(@RequestBody RecommendedAgeRest recommendedAgeRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendedAgeService.updateRecommendedAge(recommendedAgeRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RECOMMENDED_AGE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendedAgeRest> deleteRecommendedAge(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendedAgeService.deleteRecommendedAge(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.RECOMMENDED_AGE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<RecommendedAgeRest> deleteRecommendedAgePhysically(@PathVariable Long id)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				recommendedAgeService.deleteRecommendedAgePhysically(id));
	}

}
