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
import com.marsoft.adminvic.domain.response.SubscriptionRest;
import com.marsoft.adminvic.persistence.entity.Subscription;
import com.marsoft.adminvic.persistence.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	private ModelMapper modelMapper = new ModelMapper();

	private static final String ERROR_MESSAGE = "ERROR: ";
	private static final String SUBSCRIPTION_NOT_FOUND = "Subscription not found!";
	private static final String SUBSCRIPTIONS_NOT_FOUND = "Subscriptions not found!";

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public SubscriptionRest getSubscriptionById(Long id) throws AdminVicException {
		log.info("Geting subscription...");
		SubscriptionRest subscriptionResponse = null;
		try {
			subscriptionResponse = modelMapper.map(subscriptionRepository.findById(id).orElse(null),
					SubscriptionRest.class);
			if (subscriptionResponse != null) {
				log.info("Subscription found");
			} else {
				throw new NotFoundException(SUBSCRIPTION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return subscriptionResponse;
	}

	@Override
	public List<SubscriptionRest> getAllSubscriptions() throws AdminVicException {
		log.info("Geting all available subscriptions...");
		List<SubscriptionRest> subscriptionsResponseList = null;
		try {
			/*
			 * need to create an primary index in couchbase to use findAll because it used
			 * N1QL. CREATE PRIMARY INDEX `adminvic_primary_index` ON
			 * `default`:`vicod`.`dev`.`subscription` USING GSI;
			 */
			subscriptionsResponseList = subscriptionRepository.findAll().stream()
					.map(subscription -> modelMapper.map(subscription, SubscriptionRest.class))
					.collect(Collectors.toList());
			if (!subscriptionsResponseList.isEmpty()) {
				log.info("Subscriptions found");
			} else {
				throw new NotFoundException(SUBSCRIPTIONS_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return subscriptionsResponseList;
	}

	@Override
	@Transactional
	public SubscriptionRest createSubscription(SubscriptionRest subscriptionRest) throws AdminVicException {
		log.info("Creating subscription...");
		SubscriptionRest subscriptionResponse = null;
		try {
			Subscription subscription = modelMapper.map(subscriptionRest, Subscription.class);
			subscription.setInsertDate(String.valueOf(new Date()));
			subscriptionResponse = modelMapper.map(subscriptionRepository.save(subscription), SubscriptionRest.class);
			log.info("Subscription created");
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return subscriptionResponse;
	}

	@Override
	@Transactional
	public SubscriptionRest updateSubscription(SubscriptionRest subscriptionRest) throws AdminVicException {
		log.info("Updating subscription...");
		SubscriptionRest subscriptionResponse = modelMapper
				.map(subscriptionRepository.findById(subscriptionRest.getId()).orElse(null), SubscriptionRest.class);
		if (subscriptionResponse != null) {
			try {
				Subscription subscription = modelMapper.map(subscriptionRest, Subscription.class);
				subscription.setUpdatedDate(String.valueOf(new Date()));
				subscription = subscriptionRepository.save(subscription);
				subscriptionResponse = modelMapper.map(subscription, SubscriptionRest.class);
				log.info("Subscription updated");
			} catch (Exception e) {
				StringBuilder sb = new StringBuilder();
				sb.append(ERROR_MESSAGE);
				sb.append(e.getMessage());
				throw new NotFoundException(sb.toString());
			}
		} else {
			throw new NotFoundException(SUBSCRIPTION_NOT_FOUND);
		}
		return subscriptionResponse;
	}

	@Override
	@Transactional
	public SubscriptionRest deleteSubscription(Long id) throws AdminVicException {
		log.info("Deliting subscription...");
		SubscriptionRest subscriptionResponse = null;
		try {
			Subscription subscription = subscriptionRepository.findById(id).orElse(null);
			if (subscription != null) {
				subscription.setDeleted(true);
				subscription = subscriptionRepository.save(subscription);
				subscriptionResponse = modelMapper.map(subscription, SubscriptionRest.class);
				log.info("Subscription deleted");
			} else {
				throw new NotFoundException(SUBSCRIPTION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return subscriptionResponse;
	}

	@Override
	@Transactional
	public SubscriptionRest deleteSubscriptionPhysically(Long id) throws AdminVicException {
		log.info("Deliting subscription physically...");
		SubscriptionRest subscriptionResponse = null;
		try {
			Subscription subscription = subscriptionRepository.findById(id).orElse(null);
			if (subscription != null) {
				subscriptionRepository.delete(subscription);
				subscriptionResponse = modelMapper.map(subscription, SubscriptionRest.class);
				log.info("Subscription deleted physically");
			} else {
				throw new NotFoundException(SUBSCRIPTION_NOT_FOUND);
			}
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(ERROR_MESSAGE);
			sb.append(e.getMessage());
			throw new NotFoundException(sb.toString());
		}
		return subscriptionResponse;
	}

}
