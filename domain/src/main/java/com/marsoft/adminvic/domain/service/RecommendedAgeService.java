package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.RecommendedAgeRest;

public interface RecommendedAgeService {

	RecommendedAgeRest getRecommendedAgeById(Long id) throws AdminVicException;

	List<RecommendedAgeRest> getAllRecommendedAges() throws AdminVicException;

	RecommendedAgeRest createRecommendedAge(RecommendedAgeRest recommendedAgeRest) throws AdminVicException;

	RecommendedAgeRest updateRecommendedAge(RecommendedAgeRest recommendedAgeRest) throws AdminVicException;

	RecommendedAgeRest deleteRecommendedAge(Long id) throws AdminVicException;

	RecommendedAgeRest deleteRecommendedAgePhysically(Long id) throws AdminVicException;

}
