package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Tvserie;

public interface TvserieRepository extends CouchbaseRepository<Tvserie, Long> {

}
