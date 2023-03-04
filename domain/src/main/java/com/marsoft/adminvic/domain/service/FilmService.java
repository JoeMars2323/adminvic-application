package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.FilmRest;

public interface FilmService {

	FilmRest getFilmById(Long id) throws AdminVicException;

	List<FilmRest> getAllFilms() throws AdminVicException;

	FilmRest createFilm(FilmRest filmRest) throws AdminVicException;

	FilmRest updateFilm(FilmRest filmRest) throws AdminVicException;

	FilmRest deleteFilm(Long id) throws AdminVicException;

	FilmRest deleteFilmPhysically(Long id) throws AdminVicException;

}
