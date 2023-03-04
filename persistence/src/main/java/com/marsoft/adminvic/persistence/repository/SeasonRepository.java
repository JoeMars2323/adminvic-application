package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Season;

public interface SeasonRepository extends CouchbaseRepository<Season, Long> {

}
