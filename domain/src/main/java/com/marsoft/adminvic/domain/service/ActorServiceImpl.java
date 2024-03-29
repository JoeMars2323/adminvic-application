package com.marsoft.adminvic.domain.service;

import java.util.ArrayList;
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
import com.marsoft.adminvic.domain.response.ActorRest;
import com.marsoft.adminvic.domain.response.AwardRest;
import com.marsoft.adminvic.domain.utils.LogsConstants;
import com.marsoft.adminvic.persistence.entity.Actor;
import com.marsoft.adminvic.persistence.entity.Award;
import com.marsoft.adminvic.persistence.repository.ActorRepository;
import com.marsoft.adminvic.persistence.repository.AwardRepository;

@Service
public class ActorServiceImpl implements ActorService {

	private Logger log = LoggerFactory.getLogger(ActorServiceImpl.class);
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private AwardRepository awardRepository;

	@Override
	public ActorRest getActorById(Long id) throws AdminVicException {
		log.info(LogsConstants.CREATING_ACTOR);
		ActorRest actorResponse = null;
		try {
			actorResponse = modelMapper.map(actorRepository.findById(id).orElse(null), ActorRest.class);
			if (actorResponse != null) {
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
	public ActorRest getActorByName(String actorName) throws AdminVicException {
		log.info(LogsConstants.GETTING_ACTOR);
		List<AwardRest> awardRestList = new ArrayList<>();
		ActorRest actorResponse = null;
		try {
			actorResponse = modelMapper.map(actorRepository.findByName(actorName), ActorRest.class);
			if (actorResponse != null) {
				List<Award> awardList = awardRepository.getAwardsByActorId(actorResponse.getId());
				if (awardList != null) {
					for (Award a : awardList) {
						AwardRest awardRest = modelMapper.map(a, AwardRest.class);
						awardRestList.add(awardRest);
					}
					actorResponse.setAwardsList(awardRestList);
				}
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
	public List<ActorRest> getAllActors() throws AdminVicException {
		log.info(LogsConstants.GETTING_ALL_ACTORS);
		List<ActorRest> actorsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`adminvic`.`dev`.`actor` USING GSI;
			 */
			actorsResponseList = actorRepository.findAll().stream().filter(x -> !x.getDeleted())
					.map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList());
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
	public ActorRest createActor(ActorRest actorRest) throws AdminVicException {
		log.info(LogsConstants.CREATING_ACTOR);
		ActorRest actorResponse = null;
		try {
			actorRest.setId(actorRepository.getLastId() + 1);
			Actor actor = modelMapper.map(actorRest, Actor.class);
			actor.setInsertDate(String.valueOf(new Date()));
			actor.setDeleted(false);
			actor.setChanged(true);
			actorResponse = modelMapper.map(actorRepository.save(actor), ActorRest.class);
			if (actorResponse != null) {
				log.info(LogsConstants.ACTOR_CREATED);
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
	public ActorRest updateActor(ActorRest actorRest) throws AdminVicException {
		log.info(LogsConstants.UPDATING_ACTOR);
		ActorRest actorResponse = modelMapper.map(actorRepository.findById(actorRest.getId()).orElse(null),
				ActorRest.class);
		if (actorResponse != null) {
			try {
				Actor actor = modelMapper.map(actorRest, Actor.class);
				actor.setUpdatedDate(String.valueOf(new Date()));
				actor = actorRepository.save(actor);
				actorResponse = modelMapper.map(actor, ActorRest.class);
				log.info(LogsConstants.ACTOR_UPDATED);
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
	public ActorRest deleteActor(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_ACTOR);
		ActorRest actorResponse = null;
		try {
			Actor actor = actorRepository.findById(id).orElse(null);
			if (actor != null) {
				actor.setDeleted(true);
				actor = actorRepository.save(actor);
				actorResponse = modelMapper.map(actor, ActorRest.class);
				log.info(LogsConstants.ACTOR_DELETED);
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
	public ActorRest deleteActorPhysically(Long id) throws AdminVicException {
		log.info(LogsConstants.DELETING_ACTOR_PHISICALLY);
		ActorRest actorResponse = null;
		try {
			Actor actor = actorRepository.findById(id).orElse(null);
			if (actor != null) {
				actorRepository.delete(actor);
				actorResponse = modelMapper.map(actor, ActorRest.class);
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