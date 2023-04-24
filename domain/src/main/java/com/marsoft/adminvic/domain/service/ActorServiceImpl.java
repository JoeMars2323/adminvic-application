package com.marsoft.adminvic.domain.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import com.marsoft.adminvic.persistence.solr.repository.ActorSolrRepository;

@Service
public class ActorServiceImpl implements ActorService {

	private Logger log = LoggerFactory.getLogger(ActorServiceImpl.class);
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private ActorSolrRepository actorSolrRepository;

	@Override
	public ActorSolr getActorById(Long id) throws AdminVicException {
		log.info(LogsConstants.CREATING_ACTOR);
		ActorSolr actorResponse = null;
		try {
			// retrieve the item only if not deleted
			actorResponse = actorSolrRepository.findById(id).orElse(null);
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
	public List<ActorSolr> getActorByName(String actorName) throws AdminVicException {
		log.info(LogsConstants.GETTING_ACTOR);
		List<ActorSolr> actorList;
		try {
			actorList = actorSolrRepository.findByName(actorName);
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
			// convert the iterable response to actors list
			Iterable<ActorSolr> iterable = actorSolrRepository.findAll();
			// will retrieve only if is not deleted;
			actorsResponseList = StreamSupport.stream(iterable.spliterator(), false).filter(x -> !x.getDeleted())
					.collect(Collectors.toList());

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
	public ActorSolr createActor(ActorSolr actorsolr) throws AdminVicException {
		log.info(LogsConstants.CREATING_ACTOR);
		ActorSolr actorResponse = null;
		try {
			// add manually document id
			actorsolr.setId(actorRepository.getLastId() + 1);

			Actor actor = modelMapper.map(actorsolr, Actor.class);

			// set variables to insert in couchbase
			actor.setInsertDate(String.valueOf(new Date()));
			actor.setDeleted(false);
			actor.setChanged(true);

			// insert in couchbase
			actorResponse = modelMapper.map(actorRepository.save(actor), ActorSolr.class);

			// set variables to insert in solr
			actorResponse.setInsertDate(String.valueOf(new Date()));
			actorResponse.setDeleted(false);
			actorResponse.setChanged(true);

			// insert in apache solr
			actorSolrRepository.save(actorResponse);
			log.info(LogsConstants.ACTOR_CREATED_COUCHBASE);
			log.info(LogsConstants.ACTOR_CREATED_SOLR);

		} catch (Exception e) {
			log.error(LogsConstants.ACTOR_NOT_CREATED);
			log.error(e.getLocalizedMessage());
			StringBuilder sb = new StringBuilder();
			sb.append(LogsConstants.ACTOR_NOT_CREATED);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return actorResponse;
	}

	@Override
	@Transactional
	public ActorSolr updateActor(ActorSolr actorsolr) throws AdminVicException {
		log.info(LogsConstants.UPDATING_ACTOR);
		ActorSolr actorResponse = modelMapper.map(actorRepository.findById(actorsolr.getId()).orElse(null),
				ActorSolr.class);
		if (actorResponse != null) {
			try {

				Actor actor = modelMapper.map(actorsolr, Actor.class);
				actor.setUpdatedDate(String.valueOf(new Date()));
				actor.setChanged(true);

				// update in couchbase
				actor = actorRepository.save(actor);
				actorResponse = modelMapper.map(actor, ActorSolr.class);
				log.info(LogsConstants.ACTOR_UPDATED);

				actorResponse.setUpdatedDate(String.valueOf(new Date()));
				actorResponse.setChanged(true);

				// update in apache solr
				actorSolrRepository.save(actorResponse);

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

				if (actorResponse != null) {
					// delete logically in solr
					actorResponse.setDeleted(true);
					actorSolrRepository.save(actorResponse);
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
				actorSolrRepository.delete(actorResponse);
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