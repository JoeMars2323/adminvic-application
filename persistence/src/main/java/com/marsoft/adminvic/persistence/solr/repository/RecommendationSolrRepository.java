package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.RecommendationSolr;

public interface RecommendationSolrRepository extends SolrCrudRepository<RecommendationSolr, Long> {

}
