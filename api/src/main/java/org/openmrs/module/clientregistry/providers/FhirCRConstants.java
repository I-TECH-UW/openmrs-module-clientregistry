/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clientregistry.providers;

import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import ca.uhn.fhir.rest.gclient.UriClientParam;

public class FhirCRConstants {
	
	public static final String IHE_PIX_OPERATION = "$ihe-pix";
	
	@SearchParamDefinition(name = "sourceIdentifier", path = "Patient.sourceIdentifier", description = "A patient identifier used to find cross-matching identifiers in client registry", type = "token")
	public static final String SOURCE_IDENTIFIER = "sourceIdentifier";
	
	public static final TokenClientParam SOURCE_IDENTIFIER_PARAM = new TokenClientParam("sourceIdentifier");
	
	@SearchParamDefinition(name = "targetSystem", path = "Patient.targetSystem", description = "Assigning Authorities for the Patient Identifier Domains from which the returned identifiers shall be selected", type = "token")
	public static final String TARGET_SYSTEM = "targetSystem";
	
	public static final UriClientParam TARGET_SYSTEM_PARAM = new UriClientParam("targetSystem");
	
	@SearchParamDefinition(name = "_format", path = "Patient.targetSystem", description = "Assigning Authorities for the Patient Identifier Domains from which the returned identifiers shall be selected", type = "token")
	public static final String _FORMAT = "_FORMAT";
	
	public static final StringClientParam _FORMAT_PARAM = new StringClientParam("_format");
}
