package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.RecommendedAgeSolr;

public interface RecommendedAgeService {

	RecommendedAgeSolr getRecommendedAgeById(Long id) throws AdminVicException;

	List<RecommendedAgeSolr> getAllRecommendedAges() throws AdminVicException;

	RecommendedAgeSolr createRecommendedAge(RecommendedAgeSolr recommendedAgeRest) throws AdminVicException;

	RecommendedAgeSolr updateRecommendedAge(RecommendedAgeSolr recommendedAgeRest) throws AdminVicException;

	RecommendedAgeSolr deleteRecommendedAge(Long id) throws AdminVicException;

	RecommendedAgeSolr deleteRecommendedAgePhysically(Long id) throws AdminVicException;

}
