package org.openmrs.module.clientregistry.api;

import org.hl7.fhir.r4.model.Patient;
import org.openmrs.module.fhir2.api.search.param.PatientSearchParams;

import java.util.List;

public interface CRPatientService {
	
	List<Patient> getCRPatients(String sourceIdentifier, String sourceIdentifierSystem, List<String> extraTargetSystems);
	
	List<Patient> searchCRForPatients(PatientSearchParams patientSearchParams);
	
	// Query a patient through a PIXm manager using native IDs
	// Question: Should this return a patient bundle?
	Patient getPatientByPIX(String sourceIdentifier, String sourceIdentifierSystem, List<String> extraTargetSystems);
	
	// Method for patient search, including fuzzy search
	List<Patient> searchPatients(PatientSearchParams patientSearchParams);
	
	// Creates or updates a patient record
	Patient createOrUpdatePatient(Patient patient);
	
	// Purges patient record from the registry
	Patient purgePatient(Patient patient);
}
