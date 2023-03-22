package org.openmrs.module.clientregistry.api.impl;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.module.clientregistry.FhirClient;
import org.openmrs.module.clientregistry.api.ClientRegistryPatientService;
import org.openmrs.module.fhir2.api.search.param.PatientSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("clientregistry.FhirPatientService")
public class FhirPatientServiceImpl implements ClientRegistryPatientService {
	
	@Autowired
	private FhirClient fhirClient;
	
	@Override
	public Patient getPatient(String identifier) {
		
		try {
			return fhirClient.fetchPatientByConfiguredId(identifier);
		}
		catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	@Override
	public IBundleProvider searchForPatients(PatientSearchParams patientSearchParams) {
		return null;
	}
	
	@Override
	public List<PatientIdentifier> savePatient(Patient newPatient) {
		return null;
	}
}

//create client beans implementing (search, post) functions that are scannable by openmrs