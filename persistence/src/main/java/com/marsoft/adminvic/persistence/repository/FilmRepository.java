package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import com.marsoft.adminvic.persistence.entity.Film;

public interface FilmRepository extends CouchbaseRepository<Film, Long> {

	@Query("select max(meta().id) from `adminvic`.`dev`.`film` data")
	public Long getLastId();

}
