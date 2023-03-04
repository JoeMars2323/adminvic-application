package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.RecommendationRest;

public interface RecommendationService {

	RecommendationRest getRecommendationById(Long id) throws AdminVicException;

	List<RecommendationRest> getAllRecommendations() throws AdminVicException;

	RecommendationRest createRecommendation(RecommendationRest recommendationRest) throws AdminVicException;

	RecommendationRest updateRecommendation(RecommendationRest recommendationRest) throws AdminVicException;

	RecommendationRest deleteRecommendation(Long id) throws AdminVicException;

	RecommendationRest deleteRecommendationPhysically(Long id) throws AdminVicException;

}
