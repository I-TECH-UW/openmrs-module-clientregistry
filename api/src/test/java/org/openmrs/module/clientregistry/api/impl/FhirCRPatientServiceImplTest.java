/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.clientregistry.api.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Tests All Crud Methods in @FhirCRPatientServiceImpl Service
 * 
 * @see org.openmrs.module.clientregistry.api.impl.FhirCRPatientServiceImpl
 */
public class FhirCRPatientServiceImplTest {
	
	private static final Integer PATIENT_ID = 198;
	
	private static final String PATIENT_UUID = "34009h32-34h3j4-34pk34-0422y";
	
	private static final String WRONG_PATIENT_UUID = "Wrong uuid";
	
	private static final String PATIENT_GIVEN_NAME = "Mary";
	
	private static final String PATIENT_GIVEN_NAME_NOT_MATCHED = "Kakooza";
	
	private static final String PATIENT_FAMILY_NAME_NOT_MATCHED = "your fam";
	
	private static final String PATIENT_PARTIAL_GIVEN_NAME = "Nansubuga";
	
	private static final String PATIENT_FAMILY_NAME = "Kizza";
	
	private static final String PATIENT_PARTIAL_FAMILY_NAME = "Patricia";
	
	private static final String GENDER = "F";
	
	private static final String WRONG_GENDER = "wrong-gender";
	
	private static final String DATE = "1980-12-12";
	
	private static final String UNKNOWN_DATE = "0001-10-10";
	
	private static final String CITY = "Kampala";
	
	private static final String STATE = "Central";
	
	private static final String POSTAL_CODE = "256";
	
	private static final String COUNTRY = "Uganda";
	
	private static final String UNKNOWN_ADDRESS = "Kansanga";
	
	private static final String LAST_UPDATED_DATE = "2021-09-03";
	
	private static final String WRONG_LAST_UPDATED_DATE = "2020-09-09";
	
	//	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void before() {
	}
	
	/**
	 * @see FhirCRPatientServiceImpl#getCRPatients(String sourceIdentifier, String
	 *      sourceIdentifierSystem, List <String> targetSystems)
	 */
	@Test
	public void createOrUpdatePatient_shouldSaveOrUpdatePatient() {
		
	}
}
