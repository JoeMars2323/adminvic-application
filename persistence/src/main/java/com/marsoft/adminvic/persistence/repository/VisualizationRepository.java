package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Visualization;

public interface VisualizationRepository extends CouchbaseRepository<Visualization, Long> {

}
