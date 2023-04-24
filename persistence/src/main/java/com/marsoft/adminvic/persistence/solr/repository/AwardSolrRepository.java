package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.AwardSolr;

public interface AwardSolrRepository extends SolrCrudRepository<AwardSolr, Long> {

}
