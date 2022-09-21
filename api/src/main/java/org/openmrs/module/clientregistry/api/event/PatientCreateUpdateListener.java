package org.openmrs.module.clientregistry.api.event;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.openmrs.api.context.Daemon;
import org.openmrs.event.EventListener;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.clientregistry.ClientRegistryConfig;
import org.openmrs.module.clientregistry.ClientRegistryConstants;
import org.openmrs.module.fhir2.api.FhirPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientCreateUpdateListener implements EventListener {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private DaemonToken daemonToken;
	
	@Autowired
	private FhirPatientService patientService;
	
	@Autowired
	private ClientRegistryConfig config;
	
	@Autowired
	private IGenericClient client;
	
	public DaemonToken getDaemonToken() {
		return daemonToken;
	}
	
	public void setDaemonToken(DaemonToken daemonToken) {
		this.daemonToken = daemonToken;
	}
	
	@Override
	public void onMessage(Message message) {
		log.trace(String.format("Received message: \n%s", message));

		try {
			Daemon.runInDaemonThread(() -> {
				try {
					processMessage(message);
				}
				catch (Exception e) {
					log.error(String.format("Failed to process Patient message!\n%s", message.toString()), e);
				}
			}, daemonToken);
		} catch (Exception e) {
			log.error(String.format("Failed to start Daemon thread to process message!\n%s", message.toString()), e);
		}

	}
	
	private void processMessage(Message message) throws JMSException {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			
			String uuid;
			try {
				uuid = mapMessage.getString("uuid");
				log.debug(String.format("Handling patient %s", uuid));
			}
			catch (JMSException e) {
				log.error("Exception caught while trying to get patient uuid for event.", e);
				return;
			}
			
			if (uuid == null || StringUtils.isBlank(uuid)) {
				return;
			}
			
			Patient patient;
			patient = patientService.get(uuid);
			patient.getNameFirstRep().setUse(HumanName.NameUse.OFFICIAL);
			
			Identifier openmrsUniqueId = new Identifier()
			        .setSystem(ClientRegistryConstants.CLIENT_REGISTRY_INTERNAL_ID_SYSTEM)
			        .setValue(String.format("%s/%s", config.getClientRegistryIdentifierRoot(), uuid))
			        .setUse(Identifier.IdentifierUse.OFFICIAL);
			patient.addIdentifier(openmrsUniqueId);
			
			patient.setId(openmrsUniqueId.getValue());
			
			if (mapMessage.getJMSDestination().toString().equals(ClientRegistryConstants.UPDATE_MESSAGE_DESTINATION)) {
				client.update().resource(patient).execute();
			} else {
				client.create().resource(patient).execute();
			}
		}
	}
	
}
