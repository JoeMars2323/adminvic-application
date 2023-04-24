package com.marsoft.adminvic.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.service.ActorService;
import com.marsoft.adminvic.persistence.solr.entity.ActorSolr;
import com.marsoft.adminvic.web.utils.RestConstants;

@CrossOrigin
@RestController
@RequestMapping(RestConstants.ACTORS)
public class ActorController {

	@Autowired
	private ActorService actorService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.ACTOR_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActorSolr getActorById(@PathVariable Long id) throws AdminVicException {
		return actorService.getActorById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.ACTOR_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActorSolr> getActorByName(@PathVariable String name) throws AdminVicException {
		return actorService.getActorByName(name);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActorSolr> getAllActors() throws AdminVicException {
		return actorService.getAllActors();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ActorSolr createActor(@RequestBody ActorSolr actorRest) throws AdminVicException {
		return actorService.createActor(actorRest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ActorSolr updateActor(@RequestBody ActorSolr actorRest) throws AdminVicException {
		return actorService.updateActor(actorRest);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.ACTOR_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActorSolr deleteActor(@PathVariable Long id) throws AdminVicException {
		return actorService.deleteActor(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.ACTOR_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActorSolr deleteActorPhysically(@PathVariable Long id) throws AdminVicException {
		return actorService.deleteActorPhysically(id);
	}

}
