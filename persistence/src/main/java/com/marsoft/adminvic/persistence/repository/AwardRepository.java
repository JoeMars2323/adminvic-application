package com.marsoft.adminvic.persistence.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import com.marsoft.adminvic.persistence.entity.Award;

public interface AwardRepository extends CouchbaseRepository<Award, Long> {

	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND $1 IN actorIdsList")
	public List<Award> getAwardsByActorId(Long id);

}
