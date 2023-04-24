package com.marsoft.adminvic.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.service.FilmService;
import com.marsoft.adminvic.persistence.solr.entity.FilmSolr;
import com.marsoft.adminvic.web.utils.RestConstants;

@CrossOrigin
@RestController
@RequestMapping(RestConstants.FILMS)
public class FilmController {

	@Autowired
	private FilmService filmService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.FILM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public FilmSolr getFilmById(@PathVariable Long id) throws AdminVicException {
		return filmService.getFilmById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FilmSolr> getAllFilms() throws AdminVicException {
		return filmService.getAllFilms();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FilmSolr createFilm(@RequestBody FilmSolr filmRest) throws AdminVicException {
		return filmService.createFilm(filmRest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FilmSolr updateFilm(@RequestBody FilmSolr filmRest) throws AdminVicException {
		return filmService.updateFilm(filmRest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.FILM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public FilmSolr deleteFilm(@PathVariable Long id) throws AdminVicException {
		return filmService.deleteFilm(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.FILM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public FilmSolr deleteFilmPhysically(@PathVariable Long id) throws AdminVicException {
		return filmService.deleteFilmPhysically(id);
	}

}
