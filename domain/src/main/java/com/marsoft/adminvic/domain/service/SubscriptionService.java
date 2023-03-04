package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.SubscriptionRest;

public interface SubscriptionService {

	SubscriptionRest getSubscriptionById(Long id) throws AdminVicException;

	List<SubscriptionRest> getAllSubscriptions() throws AdminVicException;

	SubscriptionRest createSubscription(SubscriptionRest subscriptionRest) throws AdminVicException;

	SubscriptionRest updateSubscription(SubscriptionRest subscriptionRest) throws AdminVicException;

	SubscriptionRest deleteSubscription(Long id) throws AdminVicException;

	SubscriptionRest deleteSubscriptionPhysically(Long id) throws AdminVicException;

}
