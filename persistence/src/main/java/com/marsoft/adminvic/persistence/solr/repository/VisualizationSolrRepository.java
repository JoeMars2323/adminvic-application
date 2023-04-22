package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.VisualizationSolr;

public interface VisualizationSolrRepository extends SolrCrudRepository<VisualizationSolr, Long> {

}
