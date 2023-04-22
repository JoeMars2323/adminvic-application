package com.marsoft.adminvic.persistence.solr.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.marsoft.adminvic.persistence.entity.Award;
import com.marsoft.adminvic.persistence.solr.entity.AwardSolr;

public interface AwardSolrRepository extends SolrCrudRepository<AwardSolr, Long> {

	public List<Award> getAwardsByActorId(Long id);

}
