/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clientregistry;

public class ClientRegistryConstants {
	
	public static final String GP_CLIENT_REGISTRY_SERVER_URL = "clientregistry.clientRegistryServerUrl";
	
	public static final String GP_FHIR_CLIENT_REGISTRY_GET_PATIENT_ENDPOINT = "clientregistry.fhirGetPatientEndpoint";
	
	public static final String GP_CLIENT_REGISTRY_DEFAULT_PATIENT_IDENTIFIER_SYSTEM = "clientregistry.defaultPatientIdentifierSystem";
	
	public static final String GP_CLIENT_REGISTRY_USER_NAME = "clientregistry.username";
	
	public static final String GP_CLIENT_REGISTRY_PASSWORD = "clientregistry.password";
	
	public static final String GP_CLIENT_REGISTRY_IDENTIFIER_ROOT = "clientregistry.identifierRoot";
	
	public static final String GP_CLIENT_REGISTRY_TRANSACTION_METHOD = "clientregistry.transactionMethod";
	
	public static final String UPDATE_MESSAGE_DESTINATION = "topic://UPDATED:org.openmrs.Patient";
	
	public static final String CLIENT_REGISTRY_INTERNAL_ID_SYSTEM = "http://clientregistry.org/openmrs";
}
