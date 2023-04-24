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
import com.marsoft.adminvic.persistence.entity.Actor;
import com.marsoft.adminvic.persistence.repository.ActorRepository;
import com.marsoft.adminvic.persistence.solr.entity.ActorSolr;
import com.marsoft.adminvic.persistence.solr.repository.ActorRepositorySolr;

@Service
public class ActorServiceImpl implements ActorService {

	private Logger log = LoggerFactory.getLogger(ActorServiceImpl.class);
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private ActorRepositorySolr actorRepositorySolr;

	@Override
	public ActorSolr getActorById(Long id) throws AdminVicException {
		log.info(LogsConstants.CREATING_ACTOR);
		ActorSolr actorResponse = null;
		try {
			actorResponse = modelMapper.map(actorRepository.findById(id).orElse(null), ActorSolr.class);
			if (actorResponse != null && !actorResponse.getDeleted()) {
				log.info(LogsConstants.ACTOR_FOUND);
			} else {
				log.error(LogsConstants.ACTOR_NOT_FOUND);
				throw new NotFoundException(LogsConstants.ACTOR_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorResponse;
	}

	@Override
	// only available for search engine
	public List<ActorSolr> getActorByName(String actorName) throws AdminVicException {
		log.info(LogsConstants.GETTING_ACTOR);
		List<ActorSolr> actorList;
		try {
			actorList = actorRepositorySolr.findByName(actorName);
			// first search to check if is empty
			if (!actorList.isEmpty()) {
				for (ActorSolr a : actorList) {
					if (Boolean.TRUE.equals(a.getDeleted())) {
						actorList.remove(a);
					}
				}
				// second search to check if is empty after remove potential deleted items
				if (!actorList.isEmpty()) {
					log.info(LogsConstants.ACTOR_FOUND);
				} else {
					throw new NotFoundException(LogsConstants.ACTOR_NOT_FOUND);
				}
			} else {
				log.error(LogsConstants.ACTOR_NOT_FOUND);
				throw new NotFoundException(LogsConstants.ACTOR_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorList;
	}

	@Override
	public List<ActorSolr> getAllActors() throws AdminVicException {
		log.info(LogsConstants.GETTING_ALL_ACTORS);
		List<ActorSolr> actorsResponseList = null;
		try {
			actorsResponseList = actorRepository.findAll().stream().filter(x -> !x.getDeleted())
					.map(actor -> modelMapper.map(actor, ActorSolr.class)).collect(Collectors.toList());
			if (!actorsResponseList.isEmpty()) {
				log.info(LogsConstants.ACTORS_FOUND);
			} else {
				log.error(LogsConstants.ACTORS_NOT_FOUND);
				throw new NotFoundException(LogsConstants.ACTORS_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorsResponseList;
	}

	@Override
	@Transactional
	public ActorSolr createActor(ActorSolr actorRest) throws AdminVicException {
		log.info(LogsConstants.CREATING_ACTOR);
		ActorSolr actorResponse = null;
		try {
			// add manually document id
			actorRest.setId(actorRepository.getLastId() + 1);

			Actor actor = modelMapper.map(actorRest, Actor.class);

			// set variables to insert in couchbase
			actor.setInsertDate(String.valueOf(new Date()));
			actor.setDeleted(false);
			actor.setChanged(true);

			// insert in couchbase
			actorResponse = modelMapper.map(actorRepository.save(actor), ActorSolr.class);

			// check if the insertion in couchbase was successful
			if (actorResponse != null) {
				log.info(LogsConstants.ACTOR_CREATED);

				ActorSolr actorSolr = modelMapper.map(actorRest, ActorSolr.class);

				// set variables to insert in solr
				actorSolr.setInsertDate(String.valueOf(new Date()));
				actorSolr.setDeleted(false);
				actorSolr.setChanged(true);

				// insert in apache solr
				actorRepositorySolr.save(actorSolr);
			} else {
				log.error(LogsConstants.ACTOR_NOT_CREATED);
				throw new NotFoundException(LogsConstants.ACTOR_NOT_CREATED);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorResponse;
	}

	@Override
	@Transactional
	public ActorSolr updateActor(ActorSolr actorRest) throws AdminVicException {
		log.info(LogsConstants.UPDATING_ACTOR);
		ActorSolr actorResponse = modelMapper.map(actorRepository.findById(actorRest.getId()).orElse(null),
				ActorSolr.class);
		if (actorResponse != null) {
			try {

				Actor actor = modelMapper.map(actorRest, Actor.class);
				actor.setUpdatedDate(String.valueOf(new Date()));
				actor.setChanged(true);

				// update in couchbase
				actor = actorRepository.save(actor);
				actorResponse = modelMapper.map(actor, ActorSolr.class);
				log.info(LogsConstants.ACTOR_UPDATED);

				ActorSolr actorSolr = modelMapper.map(actorRest, ActorSolr.class);
				actorSolr.setUpdatedDate(String.valueOf(new Date()));
				actorSolr.setChanged(true);

				// update in apache solr
				actorRepositorySolr.save(actorSolr);

			} catch (Exception e) {
				log.error(LogsConstants.ERROR_MESSAGE);
				log.error(e.getLocalizedMessage());
				StringBuilder sb = new StringBuilder();
				sb.append(LogsConstants.ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			log.error(LogsConstants.ACTOR_NOT_FOUND);
			throw new NotFoundException(LogsConstants.ACTOR_NOT_FOUND);
		}
		return actorResponse;
	}

	@Override
	@Transactional
	public ActorSolr deleteActor(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_ACTOR);
		ActorSolr actorResponse = null;
		try {
			Actor actor = actorRepository.findById(id).orElse(null);

			if (actor != null) {
				// delete logically the actor
				actor.setDeleted(true);
				actor = actorRepository.save(actor);
				actorResponse = modelMapper.map(actor, ActorSolr.class);
				log.info(LogsConstants.ACTOR_DELETED);

				ActorSolr actorSolr = actorRepositorySolr.findById(id).orElse(null);

				if (actorSolr != null) {
					// delete logically in solr
					actorSolr.setDeleted(true);
					actorRepositorySolr.save(actorSolr);
				}

			} else {
				log.error(LogsConstants.ACTOR_NOT_FOUND);
				throw new NotFoundException(LogsConstants.ACTOR_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorResponse;
	}

	@Override
	@Transactional
	public ActorSolr deleteActorPhysically(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_ACTOR_PHISICALLY);
		ActorSolr actorResponse = null;
		try {
			Actor actor = actorRepository.findById(id).orElse(null);
			if (actor != null) {
				actorRepository.delete(actor);
				actorResponse = modelMapper.map(actor, ActorSolr.class);
				log.info(LogsConstants.ACTOR_DELETED_PHISICALLY);
			} else {
				log.error(LogsConstants.ACTOR_NOT_FOUND);
				throw new NotFoundException(LogsConstants.ACTOR_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(LogsConstants.ERROR_MESSAGE);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorResponse;
	}

}