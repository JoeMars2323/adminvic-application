package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import com.marsoft.adminvic.persistence.entity.Tvserie;

public interface TvserieRepository extends CouchbaseRepository<Tvserie, Long> {

	@Query("select max(meta().id) from `adminvic`.`dev`.`tvserie` data")
	public Long getLastId();

}
