package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.ActorRest;
import com.marsoft.adminvic.persistence.solr.entity.ActorSolr;

public interface ActorService {

	ActorRest getActorById(Long id) throws AdminVicException;

	List<ActorRest> getAllActors() throws AdminVicException;

	ActorRest createActor(ActorRest actorRest) throws AdminVicException;

	ActorRest updateActor(ActorRest actorRest) throws AdminVicException;

	ActorRest deleteActor(Long id) throws AdminVicException;

	ActorRest deleteActorPhysically(Long id) throws AdminVicException;

	ActorSolr getActorByName(String actorName) throws AdminVicException;

}
