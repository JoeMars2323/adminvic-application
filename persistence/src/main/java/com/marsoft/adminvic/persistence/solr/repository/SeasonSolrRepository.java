package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.SeasonSolr;

public interface SeasonSolrRepository extends SolrCrudRepository<SeasonSolr, Long> {

}
