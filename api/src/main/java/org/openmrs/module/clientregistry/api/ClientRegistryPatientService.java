package org.openmrs.module.clientregistry.api;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.hl7.fhir.r4.model.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.module.fhir2.api.search.param.PatientSearchParams;

import java.util.List;

public interface ClientRegistryPatientService {
	
	IBundleProvider searchForPatients(PatientSearchParams patientSearchParams);
	
	Patient getPatient(String identifier);
	
	List<PatientIdentifier> savePatient(Patient newPatient);
}
