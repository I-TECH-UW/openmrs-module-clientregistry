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

import org.hl7.fhir.r4.model.HumanName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openmrs.Patient;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.module.fhir2.api.FhirGlobalPropertyService;
import org.openmrs.module.fhir2.api.dao.FhirPatientDao;
import org.openmrs.module.fhir2.api.dao.FhirPatientIdentifierSystemDao;
import org.openmrs.module.fhir2.api.impl.FhirPatientServiceImpl;
import org.openmrs.module.fhir2.api.search.SearchQuery;
import org.openmrs.module.fhir2.api.search.SearchQueryInclude;
import org.openmrs.module.fhir2.api.translators.PatientTranslator;

import java.util.List;

/**
 * Tests All Crud Methods in @FhirCRPatientServiceImpl Service
 * 
 * @see org.openmrs.module.clientregistry.api.impl.FhirCRPatientServiceImpl
 */
@RunWith(MockitoJUnitRunner.class)
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
	
	@Mock
	private PatientTranslator patientTranslator;
	
	@Mock
	private FhirPatientDao dao;
	
	@Mock
	private FhirPatientIdentifierSystemDao systemDao;
	
	@Mock
	private FhirGlobalPropertyService globalPropertyService;
	
	@Mock
	private SearchQueryInclude<org.hl7.fhir.r4.model.Patient> searchQueryInclude;
	
	@Mock
	private SearchQuery<Patient, org.hl7.fhir.r4.model.Patient, FhirPatientDao, PatientTranslator, SearchQueryInclude<org.hl7.fhir.r4.model.Patient>> searchQuery;
	
	private FhirPatientServiceImpl patientService;
	
	private org.hl7.fhir.r4.model.Patient fhirPatient;
	
	private Patient patient;
	
	@Before
	public void setUp() {
		patientService = new FhirPatientServiceImpl() {
			
			@Override
			protected void validateObject(org.openmrs.Patient object) {
			}
		};
		
		patientService.setDao(dao);
		patientService.setSystemDao(systemDao);
		patientService.setTranslator(patientTranslator);
		patientService.setSearchQuery(searchQuery);
		patientService.setSearchQueryInclude(searchQueryInclude);
		
		PersonName name = new PersonName();
		name.setFamilyName(PATIENT_FAMILY_NAME);
		name.setGivenName(PATIENT_GIVEN_NAME);
		
		patient = new Patient();
		patient.setUuid(PATIENT_UUID);
		patient.setGender("M");
		patient.addName(name);
		
		PersonAddress address = new PersonAddress();
		address.setCityVillage(CITY);
		address.setStateProvince(STATE);
		address.setPostalCode(POSTAL_CODE);
		address.setCountry(COUNTRY);
		
		HumanName humanName = new HumanName();
		humanName.addGiven(PATIENT_GIVEN_NAME);
		humanName.setFamily(PATIENT_FAMILY_NAME);
		
		fhirPatient = new org.hl7.fhir.r4.model.Patient();
		fhirPatient.setId(PATIENT_UUID);
		fhirPatient.addName(humanName);
	}
	
	/**
	 * @see FhirCRPatientServiceImpl#getCRPatients(String sourceIdentifier, String
	 *      sourceIdentifierSystem, List <String> targetSystems)
	 */
	@Test
	public void createOrUpdatePatient_shouldSaveOrUpdatePatient() {
		
	}
}
