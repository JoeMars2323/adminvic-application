package com.marsoft.adminvic.persistence.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.marsoft.adminvic.persistence.entity.Profile;

public interface ProfileRepository extends CouchbaseRepository<Profile, Long> {

}
