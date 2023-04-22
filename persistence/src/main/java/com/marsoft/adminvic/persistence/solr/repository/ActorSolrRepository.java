package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.ActorSolr;

public interface ActorSolrRepository extends SolrCrudRepository<ActorSolr, Long> {

	public ActorSolr findByName(String actorName);

	public Long getLastId();

}
