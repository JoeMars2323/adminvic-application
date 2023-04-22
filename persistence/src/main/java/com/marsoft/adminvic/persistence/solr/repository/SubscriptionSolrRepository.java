package com.marsoft.adminvic.persistence.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.solr.entity.SubscriptionSolr;

public interface SubscriptionSolrRepository extends SolrCrudRepository<SubscriptionSolr, Long> {

}
