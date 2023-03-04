package com.marsoft.adminvic.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories("com.marsoft.adminvic.persistence.repository")
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

	@Value("${spring.couchbase.username}")
	private String userName;

	@Value("${spring.couchbase.bucket.name}")
	private String bucketName;

	@Value("${spring.couchbase.connectionString}")
	private String connectionString;

	// @Value("${spring.couchbase.bucket.password}")
	// private String password;

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getBucketName() {
		return bucketName;
	}

	@Override
	public String getConnectionString() {
		return connectionString;
	}

	@Override
	public String getPassword() {
		return "C0uchbas3";
	}

}
