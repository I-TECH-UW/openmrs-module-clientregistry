package org.openmrs.module.clientregistry.api;

import org.hl7.fhir.r4.model.Patient;
import org.openmrs.module.fhir2.api.search.param.PatientSearchParams;

import java.util.List;

public interface CRPatientService {
	
	List<Patient> getCRPatients(String sourceIdentifier, String sourceIdentifierSystem, List<String> extraTargetSystems);
	
	List<Patient> searchCRForPatients(PatientSearchParams patientSearchParams);
	
	Patient getPatientByPIX(String sourceIdentifier, String sourceIdentifierSystem, List<String> extraTargetSystems);
	
	List<Patient> searchPatients(PatientSearchParams patientSearchParams);
	
	Patient createOrUpdatePatient(Patient patient);
	
	Patient purgePatient(Patient patient);
}
