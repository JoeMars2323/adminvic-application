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
import com.marsoft.adminvic.persistence.entity.Recommendation;
import com.marsoft.adminvic.persistence.repository.RecommendationRepository;
import com.marsoft.adminvic.persistence.solr.entity.RecommendationSolr;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	private Logger log = LoggerFactory.getLogger(RecommendationServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String RECOMMENDATION_NOT_FOUND = "Recommendation not found!";
	private static final String RECOMMENDATIONS_NOT_FOUND = "Recommendations not found!";

	@Autowired
	private RecommendationRepository recommendationRepository;

	@Override
	public RecommendationSolr getRecommendationById(Long id) throws AdminVicException {
		log.info("Geting recommendation...");
		RecommendationSolr recommendationResponse = null;
		try {
			recommendationResponse = modelMapper.map(recommendationRepository.findById(id).orElse(null),
					RecommendationSolr.class);
			if (recommendationResponse != null) {
				log.info("Recommendation found");
			} else {
				throw new NotFoundException(RECOMMENDATION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendationResponse;
	}

	@Override
	public List<RecommendationSolr> getAllRecommendations() throws AdminVicException {
		log.info("Geting all available recommendations...");
		List<RecommendationSolr> recommendationsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`recommendation` USING GSI;
			 */
			recommendationsResponseList = recommendationRepository.findAll().stream()
					.map(recommendation -> modelMapper.map(recommendation, RecommendationSolr.class))
					.collect(Collectors.toList());
			if (!recommendationsResponseList.isEmpty()) {
				log.info("Recommendations found");
			} else {
				throw new NotFoundException(RECOMMENDATIONS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendationsResponseList;
	}

	@Override
	@Transactional
	public RecommendationSolr createRecommendation(RecommendationSolr recommendationRest) throws AdminVicException {
		log.info("Creating recommendation...");
		RecommendationSolr recommendationResponse = null;
		try {
			Recommendation recommendation = modelMapper.map(recommendationRest, Recommendation.class);
			recommendation.setInsertDate(String.valueOf(new Date()));
			recommendationResponse = modelMapper.map(recommendationRepository.save(recommendation),
					RecommendationSolr.class);
			log.info("Recommendation created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendationResponse;
	}

	@Override
	@Transactional
	public RecommendationSolr updateRecommendation(RecommendationSolr recommendationRest) throws AdminVicException {
		log.info("Updating recommendation...");
		RecommendationSolr recommendationResponse = modelMapper.map(
				recommendationRepository.findById(recommendationRest.getId()).orElse(null), RecommendationSolr.class);
		if (recommendationResponse != null) {
			try {
				Recommendation recommendation = modelMapper.map(recommendationRest, Recommendation.class);
				recommendation.setUpdatedDate(String.valueOf(new Date()));
				recommendation = recommendationRepository.save(recommendation);
				recommendationResponse = modelMapper.map(recommendation, RecommendationSolr.class);
				log.info("Recommendation updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(RECOMMENDATION_NOT_FOUND);
		}
		return recommendationResponse;
	}

	@Override
	@Transactional
	public RecommendationSolr deleteRecommendation(Long id) throws AdminVicException {
		log.info("Deliting recommendation...");
		RecommendationSolr recommendationResponse = null;
		try {
			Recommendation recommendation = recommendationRepository.findById(id).orElse(null);
			if (recommendation != null) {
				recommendation.setDeleted(true);
				recommendation = recommendationRepository.save(recommendation);
				recommendationResponse = modelMapper.map(recommendation, RecommendationSolr.class);
				log.info("Recommendation deleted");
			} else {
				throw new NotFoundException(RECOMMENDATION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendationResponse;
	}

	@Override
	@Transactional
	public RecommendationSolr deleteRecommendationPhysically(Long id) throws AdminVicException {
		log.info("Deliting recommendation physically...");
		RecommendationSolr recommendationResponse = null;
		try {
			Recommendation recommendation = recommendationRepository.findById(id).orElse(null);
			if (recommendation != null) {
				recommendationRepository.delete(recommendation);
				recommendationResponse = modelMapper.map(recommendation, RecommendationSolr.class);
				log.info("Recommendation deleted physically");
			} else {
				throw new NotFoundException(RECOMMENDATION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return recommendationResponse;
	}

}
