package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.ProfileSolr;

public interface ProfileService {

	ProfileSolr getProfileById(Long id) throws AdminVicException;

	List<ProfileSolr> getAllProfiles() throws AdminVicException;

	ProfileSolr createProfile(ProfileSolr profileRest) throws AdminVicException;

	ProfileSolr updateProfile(ProfileSolr profileRest) throws AdminVicException;

	ProfileSolr deleteProfile(Long id) throws AdminVicException;

	ProfileSolr deleteProfilePhysically(Long id) throws AdminVicException;

}
