package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Chapter;

public interface ChapterRepository extends CouchbaseRepository<Chapter, Long> {

}
