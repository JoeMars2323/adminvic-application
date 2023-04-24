package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.SeasonSolr;

public interface SeasonService {

	SeasonSolr getSeasonById(Long id) throws AdminVicException;

	List<SeasonSolr> getAllSeasons() throws AdminVicException;

	SeasonSolr createSeason(SeasonSolr seasonRest) throws AdminVicException;

	SeasonSolr updateSeason(SeasonSolr seasonRest) throws AdminVicException;

	SeasonSolr deleteSeason(Long id) throws AdminVicException;

	SeasonSolr deleteSeasonPhysically(Long id) throws AdminVicException;

}
