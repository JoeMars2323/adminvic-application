package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.FilmSolr;

public interface FilmSolrRepository extends SolrCrudRepository<FilmSolr, Long> {

}
