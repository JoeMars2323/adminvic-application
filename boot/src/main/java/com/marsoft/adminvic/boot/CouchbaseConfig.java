package com.marsoft.adminvic.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@ComponentScan
@EnableCouchbaseRepositories("com.marsoft.adminvic.persistence.repository")
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

	@Value("${spring.couchbase.username}")
	private String userName;

	@Value("${spring.couchbase.bucket.name}")
	private String bucketName;

	@Value("${spring.couchbase.connectionString}")
	private String connectionString;

	@Value("${couchbasePassword}")
	private String password;

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
		return password;
	}

	@Bean
	@Override
	public CustomConversions customConversions() {
		return super.customConversions();
	}

}
