package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Category;

public interface CategoryRepository extends CouchbaseRepository<Category, Long> {

}
