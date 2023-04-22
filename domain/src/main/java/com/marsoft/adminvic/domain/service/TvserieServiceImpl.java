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
import com.marsoft.adminvic.domain.response.TvserieRest;
import com.marsoft.adminvic.domain.utils.LogsConstants;
import com.marsoft.adminvic.persistence.entity.Tvserie;
import com.marsoft.adminvic.persistence.repository.TvserieRepository;

@Service
public class TvserieServiceImpl implements TvserieService {

	private Logger log = LoggerFactory.getLogger(TvserieServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private TvserieRepository tvserieRepository;

	@Override
	public TvserieRest getTvserieById(Long id) throws AdminVicException {
		log.info(LogsConstants.GETTING_TVSERIE);
		TvserieRest tvserieResponse = null;
		try {
			tvserieResponse = modelMapper.map(tvserieRepository.findById(id).orElse(null), TvserieRest.class);
			if (tvserieResponse != null) {
				log.info(LogsConstants.TVSERIE_FOUND);
			} else {
				throw new NotFoundException(LogsConstants.TVSERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

	@Override
	public List<TvserieRest> getAllTvseries() throws AdminVicException {
		log.info(LogsConstants.GETTING_ALL_TVSERIES);
		List<TvserieRest> tvseriesResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`tvserie` USING GSI;
			 */
			tvseriesResponseList = tvserieRepository.findAll().stream().filter(x -> !x.getDeleted())
					.map(actor -> modelMapper.map(actor, TvserieRest.class)).collect(Collectors.toList());
			if (!tvseriesResponseList.isEmpty()) {
				log.info(LogsConstants.TVSERIE_FOUND);
			} else {
				throw new NotFoundException(LogsConstants.TVSERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvseriesResponseList;
	}

	@Override
	@Transactional
	public TvserieRest createTvserie(TvserieRest tvserieRest) throws AdminVicException {
		log.info(LogsConstants.CREATING_TVSERIE);
		TvserieRest tvserieResponse = null;
		try {
			tvserieRest.setId(tvserieRepository.getLastId() + 1);
			Tvserie tvserie = modelMapper.map(tvserieRest, Tvserie.class);
			tvserie.setInsertDate(String.valueOf(new Date()));
			tvserieResponse = modelMapper.map(tvserieRepository.save(tvserie), TvserieRest.class);
			log.info(LogsConstants.TVSERIE_CREATED);
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

	@Override
	@Transactional
	public TvserieRest updateTvserie(TvserieRest tvserieRest) throws AdminVicException {
		log.info(LogsConstants.UPDATING_TVSERIE);
		TvserieRest tvserieResponse = modelMapper.map(tvserieRepository.findById(tvserieRest.getId()).orElse(null),
				TvserieRest.class);
		if (tvserieResponse != null) {
			try {
				Tvserie tvserie = modelMapper.map(tvserieRest, Tvserie.class);
				tvserie.setUpdatedDate(String.valueOf(new Date()));
				tvserie = tvserieRepository.save(tvserie);
				tvserieResponse = modelMapper.map(tvserie, TvserieRest.class);
				log.info(LogsConstants.TVSERIE_UPDATED);
			} catch (Exception e) {
				log.error(LogsConstants.ERROR_MESSAGE);
				log.error(e.getLocalizedMessage());
				StringBuilder sb = new StringBuilder();
				sb.append(LogsConstants.ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(LogsConstants.TVSERIE_NOT_FOUND);
		}
		return tvserieResponse;
	}

	@Override
	@Transactional
	public TvserieRest deleteTvserie(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_TVSERIE);
		TvserieRest tvserieResponse = null;
		try {
			Tvserie tvserie = tvserieRepository.findById(id).orElse(null);
			if (tvserie != null) {
				tvserie.setDeleted(true);
				tvserie = tvserieRepository.save(tvserie);
				tvserieResponse = modelMapper.map(tvserie, TvserieRest.class);
				log.info(LogsConstants.TVSERIE_DELETED);
			} else {
				throw new NotFoundException(LogsConstants.TVSERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

	@Override
	@Transactional
	public TvserieRest deleteTvseriePhysically(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_TVSERIE_PHISICALLY);
		TvserieRest tvserieResponse = null;
		try {
			Tvserie tvserie = tvserieRepository.findById(id).orElse(null);
			if (tvserie != null) {
				tvserieRepository.delete(tvserie);
				tvserieResponse = modelMapper.map(tvserie, TvserieRest.class);
				log.info(LogsConstants.TVSERIE_DELETED_PHISICALLY);
			} else {
				throw new NotFoundException(LogsConstants.TVSERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

}
