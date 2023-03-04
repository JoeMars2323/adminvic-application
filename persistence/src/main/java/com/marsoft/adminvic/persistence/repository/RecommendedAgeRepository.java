package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.RecommendedAge;

public interface RecommendedAgeRepository extends CouchbaseRepository<RecommendedAge, Long> {

}
