package com.marsoft.adminvic.domain.service;

import java.util.List;

import com.marsoft.adminvic.domain.exception.AdminVicException;
import com.marsoft.adminvic.domain.response.TvserieRest;

public interface TvserieService {

	TvserieRest getTvserieById(Long id) throws AdminVicException;

	List<TvserieRest> getAllTvseries() throws AdminVicException;

	TvserieRest createTvserie(TvserieRest tvserieRest) throws AdminVicException;

	TvserieRest updateTvserie(TvserieRest tvserieRest) throws AdminVicException;

	TvserieRest deleteTvserie(Long id) throws AdminVicException;

	TvserieRest deleteTvseriePhysically(Long id) throws AdminVicException;

}
