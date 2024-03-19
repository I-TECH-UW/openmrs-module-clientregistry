package org.openmrs.module.clientregistry.api;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.hl7.fhir.r4.model.Patient;
import org.openmrs.module.fhir2.api.search.param.PatientSearchParams;

import java.util.List;

public interface CRPatientService {
	
	List<Patient> getCRPatient(String sourceIdentifier, String sourceIdentifierSystem, List<String> extraTargetSystems);
	
	List<Patient> searchCRForPatients(PatientSearchParams patientSearchParams);
}
