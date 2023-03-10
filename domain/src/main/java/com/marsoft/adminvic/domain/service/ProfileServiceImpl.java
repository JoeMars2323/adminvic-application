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
import com.marsoft.adminvic.domain.response.ProfileRest;
import com.marsoft.adminvic.persistence.entity.Profile;
import com.marsoft.adminvic.persistence.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	private Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String PROFILE_NOT_FOUND = "Profile not found!";
	private static final String PROFILES_NOT_FOUND = "Profiles not found!";

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public ProfileRest getProfileById(Long id) throws AdminVicException {
		log.info("Geting profile...");
		ProfileRest profileResponse = null;
		try {
			profileResponse = modelMapper.map(profileRepository.findById(id).orElse(null), ProfileRest.class);
			if (profileResponse != null) {
				log.info("Profile found");
			} else {
				throw new NotFoundException(PROFILE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return profileResponse;
	}

	@Override
	public List<ProfileRest> getAllProfiles() throws AdminVicException {
		log.info("Geting all available profiles...");
		List<ProfileRest> profilesResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`profile` USING GSI;
			 */
			profilesResponseList = profileRepository.findAll().stream()
					.map(profile -> modelMapper.map(profile, ProfileRest.class)).collect(Collectors.toList());
			if (!profilesResponseList.isEmpty()) {
				log.info("Profiles found");
			} else {
				throw new NotFoundException(PROFILES_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return profilesResponseList;
	}

	@Override
	@Transactional
	public ProfileRest createProfile(ProfileRest profileRest) throws AdminVicException {
		log.info("Creating profile...");
		ProfileRest profileResponse = null;
		try {
			Profile profile = modelMapper.map(profileRest, Profile.class);
			profile.setInsertDate(String.valueOf(new Date()));
			profileResponse = modelMapper.map(profileRepository.save(profile), ProfileRest.class);
			log.info("Profile created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return profileResponse;
	}

	@Override
	@Transactional
	public ProfileRest updateProfile(ProfileRest profileRest) throws AdminVicException {
		log.info("Updating profile...");
		ProfileRest profileResponse = modelMapper.map(profileRepository.findById(profileRest.getId()).orElse(null),
				ProfileRest.class);
		if (profileResponse != null) {
			try {
				Profile profile = modelMapper.map(profileRest, Profile.class);
				profile.setUpdatedDate(String.valueOf(new Date()));
				profile = profileRepository.save(profile);
				profileResponse = modelMapper.map(profile, ProfileRest.class);
				log.info("Profile updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(PROFILE_NOT_FOUND);
		}
		return profileResponse;
	}

	@Override
	@Transactional
	public ProfileRest deleteProfile(Long id) throws AdminVicException {
		log.info("Deliting profile...");
		ProfileRest profileResponse = null;
		try {
			Profile profile = profileRepository.findById(id).orElse(null);
			if (profile != null) {
				profile.setDeleted(true);
				profile = profileRepository.save(profile);
				profileResponse = modelMapper.map(profile, ProfileRest.class);
				log.info("Profile deleted");
			} else {
				throw new NotFoundException(PROFILE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return profileResponse;
	}

	@Override
	@Transactional
	public ProfileRest deleteProfilePhysically(Long id) throws AdminVicException {
		log.info("Deliting profile physically...");
		ProfileRest profileResponse = null;
		try {
			Profile profile = profileRepository.findById(id).orElse(null);
			if (profile != null) {
				profileRepository.delete(profile);
				profileResponse = modelMapper.map(profile, ProfileRest.class);
				log.info("Profile deleted physically");
			} else {
				throw new NotFoundException(PROFILE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return profileResponse;
	}

}
