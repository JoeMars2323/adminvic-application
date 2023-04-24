package com.marsoft.adminvic.boot;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@ComponentScan
@EnableSolrRepositories("com.marsoft.adminvic.persistence.solr.repository")
public class SolrConfig {

	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient.Builder("http://localhost:8983/solr").build();
	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) {
		return new SolrTemplate(client);
	}

}
