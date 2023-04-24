package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.SubscriptionSolr;

public interface SubscriptionService {

	SubscriptionSolr getSubscriptionById(Long id) throws AdminVicException;

	List<SubscriptionSolr> getAllSubscriptions() throws AdminVicException;

	SubscriptionSolr createSubscription(SubscriptionSolr subscriptionRest) throws AdminVicException;

	SubscriptionSolr updateSubscription(SubscriptionSolr subscriptionRest) throws AdminVicException;

	SubscriptionSolr deleteSubscription(Long id) throws AdminVicException;

	SubscriptionSolr deleteSubscriptionPhysically(Long id) throws AdminVicException;

}
