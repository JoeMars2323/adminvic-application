package com.marsoft.adminvic.persistence.solr.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.ActorSolr;

public interface ActorSolrRepository extends SolrCrudRepository<ActorSolr, Long> {

	public List<ActorSolr> findByName(String actorName);

}
