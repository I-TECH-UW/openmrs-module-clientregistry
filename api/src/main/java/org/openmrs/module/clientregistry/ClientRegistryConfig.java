/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clientregistry;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Contains module's config.
 */
@Component
public class ClientRegistryConfig {

	public final static String MODULE_PRIVILEGE = "Client Registry Privilege";

	@Autowired
	@Qualifier("adminService")
	AdministrationService administrationService;

	@Value("${CLIENTREGISTRY_SERVERURL}")
	private String serverUrl;

	@Value("${CLIENTREGISTRY_USERNAME}")
	private String username;

	@Value("${CLIENTREGISTRY_PASSWORD}")
	private String password;

	@Value("${CLIENTREGISTRY_IDENTIFIERROOT}")
	private String identifierRoot;

	public boolean clientRegistryConnectionEnabled() {
		return StringUtils.isNotBlank(getClientRegistryServerUrl());
	}

	public String getClientRegistryServerUrl() {
		return serverUrl;
	}
	
	public String getClientRegistryGetPatientEndpoint() {
		String globalPropPatientEndpoint = administrationService
		        .getGlobalProperty(ClientRegistryConstants.GP_FHIR_CLIENT_REGISTRY_GET_PATIENT_ENDPOINT);
		
		// default to Patient/$ihe-pix if patient endpoint is not defined in config
		return (globalPropPatientEndpoint == null || globalPropPatientEndpoint.isEmpty()) ? String.format("Patient/%s",
		    FhirCRConstants.IHE_PIX_OPERATION) : globalPropPatientEndpoint;
	}
	
	public String getClientRegistryDefaultPatientIdentifierSystem() {
		return administrationService
		        .getGlobalProperty(ClientRegistryConstants.GP_CLIENT_REGISTRY_DEFAULT_PATIENT_IDENTIFIER_SYSTEM);
	}
	
	public String getClientRegistryUserName() {
		return username;
	}

	public String getClientRegistryPassword() {
		return password;
	}

	public String getClientRegistryIdentifierRoot() {
		return identifierRoot;
	}
	
	public String getClientRegistryTransactionMethod() {
		return administrationService.getGlobalProperty(ClientRegistryConstants.GP_CLIENT_REGISTRY_TRANSACTION_METHOD);
	}
}
