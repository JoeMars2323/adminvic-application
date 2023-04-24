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
import com.marsoft.adminvic.persistence.entity.Season;
import com.marsoft.adminvic.persistence.repository.SeasonRepository;
import com.marsoft.adminvic.persistence.solr.entity.SeasonSolr;

@Service
public class SeasonServiceImpl implements SeasonService {

	private Logger log = LoggerFactory.getLogger(SeasonServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String SEASON_NOT_FOUND = "Season not found!";
	private static final String SEASONS_NOT_FOUND = "Seasons not found!";

	@Autowired
	private SeasonRepository seasonRepository;

	@Override
	public SeasonSolr getSeasonById(Long id) throws AdminVicException {
		log.info("Geting season...");
		SeasonSolr seasonResponse = null;
		try {
			seasonResponse = modelMapper.map(seasonRepository.findById(id).orElse(null), SeasonSolr.class);
			if (seasonResponse != null) {
				log.info("Season found");
			} else {
				throw new NotFoundException(SEASON_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return seasonResponse;
	}

	@Override
	public List<SeasonSolr> getAllSeasons() throws AdminVicException {
		log.info("Geting all available seasons...");
		List<SeasonSolr> seasonsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`season` USING GSI;
			 */
			seasonsResponseList = seasonRepository.findAll().stream()
					.map(season -> modelMapper.map(season, SeasonSolr.class)).collect(Collectors.toList());
			if (!seasonsResponseList.isEmpty()) {
				log.info("Seasons found");
			} else {
				throw new NotFoundException(SEASONS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return seasonsResponseList;
	}

	@Override
	@Transactional
	public SeasonSolr createSeason(SeasonSolr seasonRest) throws AdminVicException {
		log.info("Creating season...");
		SeasonSolr seasonResponse = null;
		try {
			Season season = modelMapper.map(seasonRest, Season.class);
			season.setInsertDate(String.valueOf(new Date()));
			seasonResponse = modelMapper.map(seasonRepository.save(season), SeasonSolr.class);
			log.info("Season created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return seasonResponse;
	}

	@Override
	@Transactional
	public SeasonSolr updateSeason(SeasonSolr seasonRest) throws AdminVicException {
		log.info("Updating season...");
		SeasonSolr seasonResponse = modelMapper.map(seasonRepository.findById(seasonRest.getId()).orElse(null),
				SeasonSolr.class);
		if (seasonResponse != null) {
			try {
				Season season = modelMapper.map(seasonRest, Season.class);
				season.setUpdatedDate(String.valueOf(new Date()));
				season = seasonRepository.save(season);
				seasonResponse = modelMapper.map(season, SeasonSolr.class);
				log.info("Season updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(SEASON_NOT_FOUND);
		}
		return seasonResponse;
	}

	@Override
	@Transactional
	public SeasonSolr deleteSeason(Long id) throws AdminVicException {
		log.info("Deliting season...");
		SeasonSolr seasonResponse = null;
		try {
			Season season = seasonRepository.findById(id).orElse(null);
			if (season != null) {
				season.setDeleted(true);
				season = seasonRepository.save(season);
				seasonResponse = modelMapper.map(season, SeasonSolr.class);
				log.info("Season deleted");
			} else {
				throw new NotFoundException(SEASON_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return seasonResponse;
	}

	@Override
	@Transactional
	public SeasonSolr deleteSeasonPhysically(Long id) throws AdminVicException {
		log.info("Deliting season physically...");
		SeasonSolr seasonResponse = null;
		try {
			Season season = seasonRepository.findById(id).orElse(null);
			if (season != null) {
				seasonRepository.delete(season);
				seasonResponse = modelMapper.map(season, SeasonSolr.class);
				log.info("Season deleted physically");
			} else {
				throw new NotFoundException(SEASON_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return seasonResponse;
	}

}
