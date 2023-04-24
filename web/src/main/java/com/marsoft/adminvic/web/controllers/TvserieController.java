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
import com.marsoft.adminvic.domain.service.TvserieService;
import com.marsoft.adminvic.persistence.solr.entity.TvserieSolr;
import com.marsoft.adminvic.web.utils.RestConstants;

@CrossOrigin
@RestController
@RequestMapping(RestConstants.TV_SERIES)
public class TvserieController {

	@Autowired
	private TvserieService tvserieService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.TV_SERIE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public TvserieSolr getTvseriesById(@PathVariable Long id) throws AdminVicException {
		return tvserieService.getTvserieById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TvserieSolr> getAllTvseries() throws AdminVicException {
		return tvserieService.getAllTvseries();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public TvserieSolr createTvserie(@RequestBody TvserieSolr tvserieRest) throws AdminVicException {
		return tvserieService.createTvserie(tvserieRest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public TvserieSolr updateTvserie(@RequestBody TvserieSolr tvserieRest) throws AdminVicException {
		return tvserieService.updateTvserie(tvserieRest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.TV_SERIE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public TvserieSolr deleteTvserie(@PathVariable Long id) throws AdminVicException {
		return tvserieService.deleteTvserie(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.TV_SERIE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public TvserieSolr deleteTvseriePhysically(@PathVariable Long id) throws AdminVicException {
		return tvserieService.deleteTvseriePhysically(id);
	}

}
