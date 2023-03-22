package org.openmrs.module.clientregistry;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import ca.uhn.fhir.rest.client.interceptor.BearerTokenAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FhirConfig {
	
	@Autowired
	private ClientRegistryConfig config;
	
	@Autowired
	@Qualifier("fhirR4")
	private FhirContext fhirContext;
	
	//@Bean
	public IGenericClient getFhirClientWithTokenAuth() throws Exception {
		
		IGenericClient fhirClient = fhirContext.newRestfulGenericClient(config.getClientRegistryServerUrl());
		
		if (!config.getClientRegistryUserName().isEmpty()) {
			BearerTokenAuthInterceptor authInterceptor = new BearerTokenAuthInterceptor("bearerToken");
			fhirClient.registerInterceptor(authInterceptor);
		}
		return fhirClient;
	}
	
	@Bean
	public IGenericClient getFhirClientWithBasicAuth() throws Exception {
		
		IGenericClient fhirClient = fhirContext.newRestfulGenericClient(config.getClientRegistryServerUrl());
		
		if (!config.getClientRegistryUserName().isEmpty()) {
			BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(config.getClientRegistryUserName(),
			        config.getClientRegistryPassword());
			fhirClient.registerInterceptor(authInterceptor);
		}
		return fhirClient;
	}
	
}
