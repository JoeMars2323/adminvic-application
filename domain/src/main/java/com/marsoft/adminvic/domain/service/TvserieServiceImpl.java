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
import com.marsoft.adminvic.persistence.entity.Tvserie;
import com.marsoft.adminvic.persistence.repository.TvserieRepository;

@Service
public class TvserieServiceImpl implements TvserieService {

	private Logger log = LoggerFactory.getLogger(TvserieServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String TV_SERIE_NOT_FOUND = "Tv serie not found!";
	private static final String TV_SERIES_NOT_FOUND = "Tv series not found!";

	@Autowired
	private TvserieRepository tvserieRepository;

	@Override
	public TvserieRest getTvserieById(Long id) throws AdminVicException {
		log.info("Geting tv serie...");
		TvserieRest tvserieResponse = null;
		try {
			tvserieResponse = modelMapper.map(tvserieRepository.findById(id).orElse(null), TvserieRest.class);
			if (tvserieResponse != null) {
				log.info("Tvserie found");
			} else {
				throw new NotFoundException(TV_SERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

	@Override
	public List<TvserieRest> getAllTvseries() throws AdminVicException {
		log.info("Geting all available tv series...");
		List<TvserieRest> tvseriesResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`tvserie` USING GSI;
			 */
			tvseriesResponseList = tvserieRepository.findAll().stream()
					.map(tvserie -> modelMapper.map(tvserie, TvserieRest.class)).collect(Collectors.toList());
			if (!tvseriesResponseList.isEmpty()) {
				log.info("Tv series found");
			} else {
				throw new NotFoundException(TV_SERIES_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvseriesResponseList;
	}

	@Override
	@Transactional
	public TvserieRest createTvserie(TvserieRest tvserieRest) throws AdminVicException {
		log.info("Creating tv serie...");
		TvserieRest tvserieResponse = null;
		try {
			Tvserie tvserie = modelMapper.map(tvserieRest, Tvserie.class);
			tvserie.setInsertDate(String.valueOf(new Date()));
			tvserieResponse = modelMapper.map(tvserieRepository.save(tvserie), TvserieRest.class);
			log.info("Tvserie created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

	@Override
	@Transactional
	public TvserieRest updateTvserie(TvserieRest tvserieRest) throws AdminVicException {
		log.info("Updating tv serie...");
		TvserieRest tvserieResponse = modelMapper.map(tvserieRepository.findById(tvserieRest.getId()).orElse(null),
				TvserieRest.class);
		if (tvserieResponse != null) {
			try {
				Tvserie tvserie = modelMapper.map(tvserieRest, Tvserie.class);
				tvserie.setUpdatedDate(String.valueOf(new Date()));
				tvserie = tvserieRepository.save(tvserie);
				tvserieResponse = modelMapper.map(tvserie, TvserieRest.class);
				log.info("Tvserie updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(TV_SERIE_NOT_FOUND);
		}
		return tvserieResponse;
	}

	@Override
	@Transactional
	public TvserieRest deleteTvserie(Long id) throws AdminVicException {
		log.info("Deliting tv serie...");
		TvserieRest tvserieResponse = null;
		try {
			Tvserie tvserie = tvserieRepository.findById(id).orElse(null);
			if (tvserie != null) {
				tvserie.setDeleted(true);
				tvserie = tvserieRepository.save(tvserie);
				tvserieResponse = modelMapper.map(tvserie, TvserieRest.class);
				log.info("Tv serie deleted");
			} else {
				throw new NotFoundException(TV_SERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

	@Override
	@Transactional
	public TvserieRest deleteTvseriePhysically(Long id) throws AdminVicException {
		log.info("Deliting tv serie physically...");
		TvserieRest tvserieResponse = null;
		try {
			Tvserie tvserie = tvserieRepository.findById(id).orElse(null);
			if (tvserie != null) {
				tvserieRepository.delete(tvserie);
				tvserieResponse = modelMapper.map(tvserie, TvserieRest.class);
				log.info("Tv serie deleted physically");
			} else {
				throw new NotFoundException(TV_SERIE_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return tvserieResponse;
	}

}
