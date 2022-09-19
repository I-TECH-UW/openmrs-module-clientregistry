/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clientregistry.api;

import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.clientregistry.ClientRegistryConfig;
import org.openmrs.module.clientregistry.Item;
import org.openmrs.module.clientregistry.exception.ClientRegistryException;
import org.springframework.transaction.annotation.Transactional;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
@Transactional(rollbackFor = .class)
public interface ClientRegistryService extends OpenmrsService {

	/**
	 * Export patient demographic record to the CR
	 * @param patientExport
	 */
	@Authorized(ClientRegistryConfig.MODULE_PRIVILEGE)
	@Transactional
	public void exportPatient(Patient patientExport) throws ClientRegistryException;

	/**
	 * Export patient demographic record to the CR
	 * @param patientExport
	 */
	public void updatePatient(Patient patientExport) throws ClientRegistryException;

}
