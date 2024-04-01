/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clientregistry;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("clientRegistryFhirConfig")
public class FhirConfig {
	
	@Autowired
	private ClientRegistryConfig config;
	
	@Autowired
	@Qualifier("fhirR4")
	private FhirContext fhirContext;
	
	@Bean(name = "clientRegistryFhirClient")
	public IGenericClient getFhirClient() throws Exception {
		IGenericClient fhirClient = fhirContext.newRestfulGenericClient(config.getClientRegistryServerUrl());
		if (!config.getClientRegistryUserName().isEmpty()) {
			BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(config.getClientRegistryUserName(),
			        config.getClientRegistryPassword());
			fhirClient.registerInterceptor(authInterceptor);
		}
		return fhirClient;
	}
	
}
