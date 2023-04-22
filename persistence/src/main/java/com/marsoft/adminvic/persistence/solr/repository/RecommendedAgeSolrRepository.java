package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.RecommendedAgeSolr;

public interface RecommendedAgeSolrRepository extends SolrCrudRepository<RecommendedAgeSolr, Long> {

}
