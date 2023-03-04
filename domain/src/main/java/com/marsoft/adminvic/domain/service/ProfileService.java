package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.ProfileRest;

public interface ProfileService {

	ProfileRest getProfileById(Long id) throws AdminVicException;

	List<ProfileRest> getAllProfiles() throws AdminVicException;

	ProfileRest createProfile(ProfileRest profileRest) throws AdminVicException;

	ProfileRest updateProfile(ProfileRest profileRest) throws AdminVicException;

	ProfileRest deleteProfile(Long id) throws AdminVicException;

	ProfileRest deleteProfilePhysically(Long id) throws AdminVicException;

}
