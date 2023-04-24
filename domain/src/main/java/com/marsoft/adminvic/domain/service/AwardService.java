package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.AwardSolr;

public interface AwardService {

	AwardSolr getAwardById(Long id) throws AdminVicException;

	List<AwardSolr> getAllAwards() throws AdminVicException;

	AwardSolr createAward(AwardSolr awardRest) throws AdminVicException;

	AwardSolr updateAward(AwardSolr awardRest) throws AdminVicException;

	AwardSolr deleteAward(Long id) throws AdminVicException;

	AwardSolr deleteAwardPhysically(Long id) throws AdminVicException;

}
