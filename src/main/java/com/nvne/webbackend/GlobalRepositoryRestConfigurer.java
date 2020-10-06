package com.nvne.webbackend;

import com.nvne.webbackend.entities.AutoEntrepreneur;
import com.nvne.webbackend.entities.Entreprise;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class GlobalRepositoryRestConfigurer implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setReturnBodyOnCreate(true);
		config.setReturnBodyOnUpdate(true);
		config.exposeIdsFor(Entreprise.class,AutoEntrepreneur.class);
		config.getCorsRegistry()
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedHeaders("*")
			.allowedMethods("OPTIONS","HEAD","GET","PUT","POST","DELETE","PATCH");
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config);
	}
	
}
