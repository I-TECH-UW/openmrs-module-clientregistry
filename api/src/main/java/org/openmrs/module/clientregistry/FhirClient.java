package org.openmrs.module.clientregistry;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smallGod
 * @date: 2023-01-23
 */
@Component
public class FhirClient {
	
	static FhirContext CTX = FhirContext.forR4();
	
	@Autowired
	private ClientRegistryConfig registry;
	
	@Autowired
	private FhirConfig config;
	
	public Patient fetchPatientByConfiguredId(String configuredId) throws Exception {

        if (configuredId == null || configuredId.isEmpty()) {
            throw new IllegalArgumentException("Invalid configured patient Id input.");
        }

        Bundle patientBundle = config.getFhirClientWithBasicAuth().search()
                .forResource(Patient.class)
                .where(Patient.IDENTIFIER.exactly().systemAndCode(registry.getClientRegistryPatientIdentifierSystem(), configuredId))
                .returnBundle(Bundle.class)
                .execute();

        List<Bundle.BundleEntryComponent> bundleOfPatients = patientBundle.getEntry();
        Patient patient = bundleOfPatients.stream()
                .findFirst()
                .map(e -> (Patient) e.getResource())
                .orElse(null);

        if (patient == null) {
            throw new Exception("Patient with ID: " + configuredId + " Not Found");
        }
        return patient;
    }
	
	public Patient createNewPatient(Patient newPatient) throws Exception {
		
		Patient createdPatient = (Patient) config.getFhirClientWithBasicAuth().create().resource(newPatient).prettyPrint()
		        .encodedJson().execute().getOperationOutcome();
		
		//TODO: save identifiers in DB
		List<PatientIdentifier> patientIds = getPatientIds(createdPatient);
		
		return createdPatient;
	}
	
	private List<PatientIdentifier> getPatientIds(Patient patient) {

        List<PatientIdentifier> patientIds = new ArrayList<>();

        for (Identifier identifier : patient.getIdentifier()) {

            if (identifier.getSystem().equalsIgnoreCase(registry.getClientRegistryPatientIdentifierSystem())) {

                patientIds.add(new PatientIdentifier(
                        identifier.getValue(),
                        Context.getPatientService().getPatientIdentifierTypeByName("Health ID"),//TODO: move this to global properties & use iniz to set the UUID so it is static
                        Context.getLocationService().getDefaultLocation()));
            }
        }
        return patientIds;
    }
}
