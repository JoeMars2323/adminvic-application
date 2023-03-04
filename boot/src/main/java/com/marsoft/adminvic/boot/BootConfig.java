package com.marsoft.adminvic.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.marsoft.adminvic.domain.DomainConfig;
import com.marsoft.adminvic.persistence.PersistenceConfig;
import com.marsoft.adminvic.web.WebConfig;

@Configuration
@Import({ WebConfig.class, DomainConfig.class, PersistenceConfig.class })
public class BootConfig {

}
