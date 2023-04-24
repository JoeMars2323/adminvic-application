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
import com.marsoft.adminvic.persistence.entity.RecommendedAge;
import com.marsoft.adminvic.persistence.repository.RecommendedAgeRepository;
import com.marsoft.adminvic.persistence.solr.entity.RecommendedAgeSolr;

@Service
public class RecommendedAgeServiceImpl implements RecommendedAgeService {

	private Logger log = LoggerFactory.getLogger(RecommendedAgeServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String RECOMMENDED_AGE_NOT_FOUND = "Recommended age not found!";
	private static final String RECOMMENDED_AGES_FOUND = "Recommended ages not found!";

	@Autowired
	private RecommendedAgeRepository recommendedAgeRepository;

	@Override
	public RecommendedAgeSolr getRecommendedAgeById(Long id) throws AdminVicException {
		log.info("Geting recommended age...");
		RecommendedAgeSolr recommendedAgeResponse = null;
		try {
			recommendedAgeResponse = modelMapper.map(recommendedAgeRepository.findById(id).orElse(null),
					RecommendedAgeSolr.class);
			if (recommendedAgeResponse != null) {
				log.info("Recommended age found");
			} else {
				throw new NotFoundException(RECOMMENDED_AGE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendedAgeResponse;
	}

	@Override
	public List<RecommendedAgeSolr> getAllRecommendedAges() throws AdminVicException {
		log.info("Geting all available recommended ages...");
		List<RecommendedAgeSolr> recommendedAgesResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`recommendedAge` USING GSI;
			 */
			recommendedAgesResponseList = recommendedAgeRepository.findAll().stream()
					.map(recommendedAge -> modelMapper.map(recommendedAge, RecommendedAgeSolr.class))
					.collect(Collectors.toList());
			if (!recommendedAgesResponseList.isEmpty()) {
				log.info("Recommended ages found");
			} else {
				throw new NotFoundException(RECOMMENDED_AGES_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendedAgesResponseList;
	}

	@Override
	@Transactional
	public RecommendedAgeSolr createRecommendedAge(RecommendedAgeSolr recommendedAgeRest) throws AdminVicException {
		log.info("Creating recommended age...");
		RecommendedAgeSolr recommendedAgeResponse = null;
		try {
			RecommendedAge recommendedAge = modelMapper.map(recommendedAgeRest, RecommendedAge.class);
			recommendedAge.setInsertDate(String.valueOf(new Date()));
			recommendedAgeResponse = modelMapper.map(recommendedAgeRepository.save(recommendedAge),
					RecommendedAgeSolr.class);
			log.info("Recommended age created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendedAgeResponse;
	}

	@Override
	@Transactional
	public RecommendedAgeSolr updateRecommendedAge(RecommendedAgeSolr recommendedAgeRest) throws AdminVicException {
		log.info("Updating recommended age...");
		RecommendedAgeSolr recommendedAgeResponse = modelMapper.map(
				recommendedAgeRepository.findById(recommendedAgeRest.getId()).orElse(null), RecommendedAgeSolr.class);
		if (recommendedAgeResponse != null) {
			try {
				RecommendedAge recommendedAge = modelMapper.map(recommendedAgeRest, RecommendedAge.class);
				recommendedAge.setUpdatedDate(String.valueOf(new Date()));
				recommendedAge = recommendedAgeRepository.save(recommendedAge);
				recommendedAgeResponse = modelMapper.map(recommendedAge, RecommendedAgeSolr.class);
				log.info("Recommended age updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(RECOMMENDED_AGE_NOT_FOUND);
		}
		return recommendedAgeResponse;
	}

	@Override
	@Transactional
	public RecommendedAgeSolr deleteRecommendedAge(Long id) throws AdminVicException {
		log.info("Deliting recommended age...");
		RecommendedAgeSolr recommendedAgeResponse = null;
		try {
			RecommendedAge recommendedAge = recommendedAgeRepository.findById(id).orElse(null);
			if (recommendedAge != null) {
				recommendedAge.setDeleted(true);
				recommendedAge = recommendedAgeRepository.save(recommendedAge);
				recommendedAgeResponse = modelMapper.map(recommendedAge, RecommendedAgeSolr.class);
				log.info("Recommended age deleted");
			} else {
				throw new NotFoundException(RECOMMENDED_AGE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendedAgeResponse;
	}

	@Override
	@Transactional
	public RecommendedAgeSolr deleteRecommendedAgePhysically(Long id) throws AdminVicException {
		log.info("Deliting recommended age physically...");
		RecommendedAgeSolr recommendedAgeResponse = null;
		try {
			RecommendedAge recommendedAge = recommendedAgeRepository.findById(id).orElse(null);
			if (recommendedAge != null) {
				recommendedAgeRepository.delete(recommendedAge);
				recommendedAgeResponse = modelMapper.map(recommendedAge, RecommendedAgeSolr.class);
				log.info("Recommended age deleted physically");
			} else {
				throw new NotFoundException(RECOMMENDED_AGE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendedAgeResponse;
	}

}
