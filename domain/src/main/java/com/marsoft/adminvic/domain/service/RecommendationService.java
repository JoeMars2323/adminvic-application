package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.RecommendationSolr;

public interface RecommendationService {

	RecommendationSolr getRecommendationById(Long id) throws AdminVicException;

	List<RecommendationSolr> getAllRecommendations() throws AdminVicException;

	RecommendationSolr createRecommendation(RecommendationSolr recommendationRest) throws AdminVicException;

	RecommendationSolr updateRecommendation(RecommendationSolr recommendationRest) throws AdminVicException;

	RecommendationSolr deleteRecommendation(Long id) throws AdminVicException;

	RecommendationSolr deleteRecommendationPhysically(Long id) throws AdminVicException;

}
