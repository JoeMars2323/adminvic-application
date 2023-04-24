package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.FilmSolr;

public interface FilmService {

	FilmSolr getFilmById(Long id) throws AdminVicException;

	List<FilmSolr> getAllFilms() throws AdminVicException;

	FilmSolr createFilm(FilmSolr filmRest) throws AdminVicException;

	FilmSolr updateFilm(FilmSolr filmRest) throws AdminVicException;

	FilmSolr deleteFilm(Long id) throws AdminVicException;

	FilmSolr deleteFilmPhysically(Long id) throws AdminVicException;

}
