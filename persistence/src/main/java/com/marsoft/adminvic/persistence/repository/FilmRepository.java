package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Film;

public interface FilmRepository extends CouchbaseRepository<Film, Long> {

}
