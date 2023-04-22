package com.marsoft.adminvic.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.AdminVicResponse;
import com.marsoft.adminvic.domain.response.SubscriptionRest;
import com.marsoft.adminvic.domain.service.SubscriptionService;
import com.marsoft.adminvic.web.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.SUBSCRIPTIONS)
@CrossOrigin(origins = RestConstants.FRONTEND_URL)
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.SUBSCRIPTION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<SubscriptionRest> getSubscriptionsById(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				subscriptionService.getSubscriptionById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<List<SubscriptionRest>> getAllSubscriptions() throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				subscriptionService.getAllSubscriptions());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<SubscriptionRest> createSubscription(@RequestBody SubscriptionRest subscriptionRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				subscriptionService.createSubscription(subscriptionRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<SubscriptionRest> updateSubscription(@RequestBody SubscriptionRest subscriptionRest)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				subscriptionService.updateSubscription(subscriptionRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.SUBSCRIPTION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<SubscriptionRest> deleteSubscription(@PathVariable Long id) throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				subscriptionService.deleteSubscription(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.SUBSCRIPTION_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminVicResponse<SubscriptionRest> deleteSubscriptionPhysically(@PathVariable Long id)
			throws AdminVicException {
		return new AdminVicResponse<>(RestConstants.SUCCESS, String.valueOf(HttpStatus.OK), RestConstants.OK,
				subscriptionService.deleteSubscriptionPhysically(id));
	}

}
