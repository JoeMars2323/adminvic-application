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
import com.marsoft.adminvic.domain.response.AwardRest;
import com.marsoft.adminvic.persistence.entity.Award;
import com.marsoft.adminvic.persistence.repository.AwardRepository;

@Service
public class AwardServiceImpl implements AwardService {

	private Logger log = LoggerFactory.getLogger(AwardServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String AWARD_NOT_FOUND = "Award not found!";
	private static final String AWARDS_NOT_FOUND = "Awards not found!";

	@Autowired
	private AwardRepository awardRepository;

	@Override
	public AwardRest getAwardById(Long id) throws AdminVicException {
		log.info("Geting award...");
		AwardRest awardResponse = null;
		try {
			awardResponse = modelMapper.map(awardRepository.findById(id).orElse(null), AwardRest.class);
			if (awardResponse != null) {
				log.info("Award found");
			} else {
				throw new NotFoundException(AWARD_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return awardResponse;
	}

	@Override
	public List<AwardRest> getAllAwards() throws AdminVicException {
		log.info("Geting all available awards...");
		List<AwardRest> awardsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`adminvic`.`dev`.`award` USING GSI;
			 */
			awardsResponseList = awardRepository.findAll().stream()
					.map(award -> modelMapper.map(award, AwardRest.class)).collect(Collectors.toList());
			if (!awardsResponseList.isEmpty()) {
				log.info("Awards found");
			} else {
				throw new NotFoundException(AWARDS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return awardsResponseList;
	}

	@Override
	@Transactional
	public AwardRest createAward(AwardRest awardRest) throws AdminVicException {
		log.info("Creating award...");
		AwardRest awardResponse = null;
		try {
			Award award = modelMapper.map(awardRest, Award.class);
			award.setInsertDate(String.valueOf(new Date()));
			awardResponse = modelMapper.map(awardRepository.save(award), AwardRest.class);
			log.info("Award created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return awardResponse;
	}

	@Override
	@Transactional
	public AwardRest updateAward(AwardRest awardRest) throws AdminVicException {
		log.info("Updating award...");
		AwardRest awardResponse = modelMapper.map(awardRepository.findById(awardRest.getId()).orElse(null),
				AwardRest.class);
		if (awardResponse != null) {
			try {
				Award award = modelMapper.map(awardRest, Award.class);
				award.setUpdatedDate(String.valueOf(new Date()));
				award = awardRepository.save(award);
				awardResponse = modelMapper.map(award, AwardRest.class);
				log.info("Award updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(AWARD_NOT_FOUND);
		}
		return awardResponse;
	}

	@Override
	@Transactional
	public AwardRest deleteAward(Long id) throws AdminVicException {
		log.info("Deliting award...");
		AwardRest awardResponse = null;
		try {
			Award award = awardRepository.findById(id).orElse(null);
			if (award != null) {
				award.setDeleted(true);
				award = awardRepository.save(award);
				awardResponse = modelMapper.map(award, AwardRest.class);
				log.info("Award deleted");
			} else {
				throw new NotFoundException(AWARD_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return awardResponse;
	}

	@Override
	@Transactional
	public AwardRest deleteAwardPhysically(Long id) throws AdminVicException {
		log.info("Deliting award physically...");
		AwardRest awardResponse = null;
		try {
			Award award = awardRepository.findById(id).orElse(null);
			if (award != null) {
				awardRepository.delete(award);
				awardResponse = modelMapper.map(award, AwardRest.class);
				log.info("Award deleted physically");
			} else {
				throw new NotFoundException(AWARD_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return awardResponse;
	}

}