package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.RecommendedAgeSolr;

public interface RecommendedAgeRepositorySolr extends SolrCrudRepository<RecommendedAgeSolr, Long> {

}
