package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import com.marsoft.adminvic.persistence.entity.Actor;

public interface ActorRepository extends CouchbaseRepository<Actor, Long> {

	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND actorName=$1")
	public Actor findByName(String actorName);

	@Query("select max(meta().id) from `adminvic`.`dev`.`actor` data")
	public Long getLastId();

}
