package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.RecommendationSolr;

public interface RecommendationRepositorySolr extends SolrCrudRepository<RecommendationSolr, Long> {

}
