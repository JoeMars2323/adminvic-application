package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.CategorySolr;

public interface CategoryRepositorySolr extends SolrCrudRepository<CategorySolr, Long> {

}