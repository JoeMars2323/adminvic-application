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
import com.marsoft.adminvic.domain.response.FilmRest;
import com.marsoft.adminvic.persistence.entity.Film;
import com.marsoft.adminvic.persistence.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {

	private Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String FILM_NOT_FOUND = "Film not found!";
	private static final String FILMS_NOT_FOUND = "Films not found!";

	@Autowired
	private FilmRepository filmRepository;

	@Override
	public FilmRest getFilmById(Long id) throws AdminVicException {
		log.info("Geting film...");
		FilmRest filmResponse = null;
		try {
			filmResponse = modelMapper.map(filmRepository.findById(id).orElse(null), FilmRest.class);
			if (filmResponse != null) {
				log.info("Film found");
			} else {
				throw new NotFoundException(FILM_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

	@Override
	public List<FilmRest> getAllFilms() throws AdminVicException {
		log.info("Geting all available films...");
		List<FilmRest> filmsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`film` USING GSI;
			 */
			filmsResponseList = filmRepository.findAll().stream().map(film -> modelMapper.map(film, FilmRest.class))
					.collect(Collectors.toList());
			if (!filmsResponseList.isEmpty()) {
				log.info("Films found");
			} else {
				throw new NotFoundException(FILMS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmsResponseList;
	}

	@Override
	@Transactional
	public FilmRest createFilm(FilmRest filmRest) throws AdminVicException {
		log.info("Creating film...");
		FilmRest filmResponse = null;
		try {
			Film film = modelMapper.map(filmRest, Film.class);
			film.setInsertDate(String.valueOf(new Date()));
			filmResponse = modelMapper.map(filmRepository.save(film), FilmRest.class);
			log.info("Film created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

	@Override
	@Transactional
	public FilmRest updateFilm(FilmRest filmRest) throws AdminVicException {
		log.info("Updating film...");
		FilmRest filmResponse = modelMapper.map(filmRepository.findById(filmRest.getId()).orElse(null), FilmRest.class);
		if (filmResponse != null) {
			try {
				Film film = modelMapper.map(filmRest, Film.class);
				film.setUpdatedDate(String.valueOf(new Date()));
				film = filmRepository.save(film);
				filmResponse = modelMapper.map(film, FilmRest.class);
				log.info("Film updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(FILM_NOT_FOUND);
		}
		return filmResponse;
	}

	@Override
	@Transactional
	public FilmRest deleteFilm(Long id) throws AdminVicException {
		log.info("Deliting film...");
		FilmRest filmResponse = null;
		try {
			Film film = filmRepository.findById(id).orElse(null);
			if (film != null) {
				film.setDeleted(true);
				film = filmRepository.save(film);
				filmResponse = modelMapper.map(film, FilmRest.class);
				log.info("Film deleted");
			} else {
				throw new NotFoundException(FILM_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

	@Override
	@Transactional
	public FilmRest deleteFilmPhysically(Long id) throws AdminVicException {
		log.info("Deliting film physically...");
		FilmRest filmResponse = null;
		try {
			Film film = filmRepository.findById(id).orElse(null);
			if (film != null) {
				filmRepository.delete(film);
				filmResponse = modelMapper.map(film, FilmRest.class);
				log.info("Film deleted physically");
			} else {
				throw new NotFoundException(FILM_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return filmResponse;
	}

}
