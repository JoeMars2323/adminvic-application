package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.ProfileSolr;

public interface ProfileSolrRepository extends SolrCrudRepository<ProfileSolr, Long> {

}
