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
import com.marsoft.adminvic.domain.service.AwardService;
import com.marsoft.adminvic.persistence.solr.entity.AwardSolr;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.AWARDS)
@CrossOrigin(origins = RestConstants.FRONTEND_URL)
public class AwardController {

	@Autowired
	private AwardService awardService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.AWARD_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<AwardSolr> getAwardById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				awardService.getAwardById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<AwardSolr>> getAllAwards() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				awardService.getAllAwards());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<AwardSolr> createAward(@RequestBody AwardSolr awardRest) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				awardService.createAward(awardRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<AwardSolr> updateAward(@RequestBody AwardSolr awardRest) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				awardService.updateAward(awardRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.AWARD_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<AwardSolr> deleteAward(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				awardService.deleteAward(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.AWARD_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<AwardSolr> deleteAwardPhysically(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				awardService.deleteAwardPhysically(id));
	}

}
