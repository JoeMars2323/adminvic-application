package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.TvserieSolr;

public interface TvserieSolrRepository extends SolrCrudRepository<TvserieSolr, Long> {

	public Long getLastId();

}
