package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.SeasonRest;

public interface SeasonService {

	SeasonRest getSeasonById(Long id) throws AdminVicException;

	List<SeasonRest> getAllSeasons() throws AdminVicException;

	SeasonRest createSeason(SeasonRest seasonRest) throws AdminVicException;

	SeasonRest updateSeason(SeasonRest seasonRest) throws AdminVicException;

	SeasonRest deleteSeason(Long id) throws AdminVicException;

	SeasonRest deleteSeasonPhysically(Long id) throws AdminVicException;

}
