package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Subscription;

public interface SubscriptionRepository extends CouchbaseRepository<Subscription, Long> {

}
