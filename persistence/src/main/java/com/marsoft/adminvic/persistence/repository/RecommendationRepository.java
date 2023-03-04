package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Recommendation;

public interface RecommendationRepository extends CouchbaseRepository<Recommendation, Long> {

}
