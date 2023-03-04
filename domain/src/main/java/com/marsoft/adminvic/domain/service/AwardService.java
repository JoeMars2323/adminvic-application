package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.AwardRest;

public interface AwardService {

	AwardRest getAwardById(Long id) throws AdminVicException;

	List<AwardRest> getAllAwards() throws AdminVicException;

	AwardRest createAward(AwardRest awardRest) throws AdminVicException;

	AwardRest updateAward(AwardRest awardRest) throws AdminVicException;

	AwardRest deleteAward(Long id) throws AdminVicException;

	AwardRest deleteAwardPhysically(Long id) throws AdminVicException;

}
