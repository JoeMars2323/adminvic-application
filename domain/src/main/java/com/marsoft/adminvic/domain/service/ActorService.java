package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.ActorSolr;

public interface ActorService {

	ActorSolr getActorById(Long id) throws AdminVicException;

	List<ActorSolr> getAllActors() throws AdminVicException;

	ActorSolr createActor(ActorSolr actorRest) throws AdminVicException;

	ActorSolr updateActor(ActorSolr actorRest) throws AdminVicException;

	ActorSolr deleteActor(Long id) throws AdminVicException;

	ActorSolr deleteActorPhysically(Long id) throws AdminVicException;

	List<ActorSolr> getActorByName(String actorName) throws AdminVicException;

}
