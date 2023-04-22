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
import com.marsoft.adminvic.domain.response.ProfileRest;
import com.marsoft.adminvic.domain.service.ProfileService;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.PROFILE)
@CrossOrigin(origins = RestConstants.FRONTEND_URL)
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.PROFILE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ProfileRest> getProfileById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				profileService.getProfileById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<ProfileRest>> getAllProfiles() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				profileService.getAllProfiles());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ProfileRest> createProfile(@RequestBody ProfileRest profileRest) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				profileService.createProfile(profileRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ProfileRest> updateProfile(@RequestBody ProfileRest profileRest) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				profileService.updateProfile(profileRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.PROFILE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ProfileRest> deleteProfile(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				profileService.deleteProfile(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.PROFILE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<ProfileRest> deleteProfilePhysically(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				profileService.deleteProfilePhysically(id));
	}

}
