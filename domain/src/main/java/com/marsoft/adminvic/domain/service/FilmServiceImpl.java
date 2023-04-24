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
import com.marsoft.adminvic.domain.utils.LogsConstants;
import com.marsoft.adminvic.persistence.entity.Film;
import com.marsoft.adminvic.persistence.repository.FilmRepository;
import com.marsoft.adminvic.persistence.solr.entity.FilmSolr;

@Service
public class FilmServiceImpl implements FilmService {

	private Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private FilmRepository filmRepository;

	@Override
	public FilmSolr getFilmById(Long id) throws AdminVicException {
		log.info(LogsConstants.GETTING_FILM);
		FilmSolr filmResponse = null;
		try {
			filmResponse = modelMapper.map(filmRepository.findById(id).orElse(null), FilmSolr.class);
			if (filmResponse != null) {
				log.info(LogsConstants.FILM_FOUND);
			} else {
				throw new NotFoundException(LogsConstants.FILM_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

	@Override
	public List<FilmSolr> getAllFilms() throws AdminVicException {
		log.info(LogsConstants.GETTING_ALL_FILMS);
		List<FilmSolr> filmsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`film` USING GSI;
			 */
			filmsResponseList = filmRepository.findAll().stream().filter(x -> !x.getDeleted())
					.map(actor -> modelMapper.map(actor, FilmSolr.class)).collect(Collectors.toList());
			if (!filmsResponseList.isEmpty()) {
				log.info(LogsConstants.FILM_FOUND);
			} else {
				throw new NotFoundException(LogsConstants.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmsResponseList;
	}

	@Override
	@Transactional
	public FilmSolr createFilm(FilmSolr filmRest) throws AdminVicException {
		log.info(LogsConstants.CREATING_FILM);
		FilmSolr filmResponse = null;
		try {
			filmRest.setId(filmRepository.getLastId() + 1);
			Film film = modelMapper.map(filmRest, Film.class);
			film.setInsertDate(String.valueOf(new Date()));
			filmResponse = modelMapper.map(filmRepository.save(film), FilmSolr.class);
			log.info(LogsConstants.FILM_CREATED);
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

	@Override
	@Transactional
	public FilmSolr updateFilm(FilmSolr filmRest) throws AdminVicException {
		log.info(LogsConstants.UPDATING_FILM);
		FilmSolr filmResponse = modelMapper.map(filmRepository.findById(filmRest.getId()).orElse(null), FilmSolr.class);
		if (filmResponse != null) {
			try {
				Film film = modelMapper.map(filmRest, Film.class);
				film.setUpdatedDate(String.valueOf(new Date()));
				film = filmRepository.save(film);
				filmResponse = modelMapper.map(film, FilmSolr.class);
				log.info(LogsConstants.FILM_UPDATED);
			} catch (Exception e) {
				log.error(LogsConstants.ERROR_MESSAGE);
				log.error(e.getLocalizedMessage());
				StringBuilder sb = new StringBuilder();
				sb.append(LogsConstants.ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(LogsConstants.ERROR_MESSAGE);
		}
		return filmResponse;
	}

	@Override
	@Transactional
	public FilmSolr deleteFilm(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_FILM);
		FilmSolr filmResponse = null;
		try {
			Film film = filmRepository.findById(id).orElse(null);
			if (film != null) {
				film.setDeleted(true);
				film = filmRepository.save(film);
				filmResponse = modelMapper.map(film, FilmSolr.class);
				log.info(LogsConstants.FILM_DELETED);
			} else {
				throw new NotFoundException(LogsConstants.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

	@Override
	@Transactional
	public FilmSolr deleteFilmPhysically(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_FILM_PHISICALLY);
		FilmSolr filmResponse = null;
		try {
			Film film = filmRepository.findById(id).orElse(null);
			if (film != null) {
				filmRepository.delete(film);
				filmResponse = modelMapper.map(film, FilmSolr.class);
				log.info(LogsConstants.FILM_DELETED_PHISICALLY);
			} else {
				throw new NotFoundException(LogsConstants.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

}
