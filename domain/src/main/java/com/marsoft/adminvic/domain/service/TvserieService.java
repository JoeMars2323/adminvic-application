package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.persistence.solr.entity.TvserieSolr;

public interface TvserieService {

	TvserieSolr getTvserieById(Long id) throws AdminVicException;

	List<TvserieSolr> getAllTvseries() throws AdminVicException;

	TvserieSolr createTvserie(TvserieSolr tvserieRest) throws AdminVicException;

	TvserieSolr updateTvserie(TvserieSolr tvserieRest) throws AdminVicException;

	TvserieSolr deleteTvserie(Long id) throws AdminVicException;

	TvserieSolr deleteTvseriePhysically(Long id) throws AdminVicException;

}
