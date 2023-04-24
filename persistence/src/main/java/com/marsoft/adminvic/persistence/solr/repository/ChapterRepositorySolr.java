package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.ChapterSolr;

public interface ChapterRepositorySolr extends SolrCrudRepository<ChapterSolr, Long> {

}