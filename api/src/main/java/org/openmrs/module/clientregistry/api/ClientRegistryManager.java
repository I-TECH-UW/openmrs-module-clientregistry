package org.openmrs.module.clientregistry.api;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.Patient;
import org.openmrs.api.GlobalPropertyListener;
import org.openmrs.event.Event;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.clientregistry.ClientRegistryConstants;
import org.openmrs.module.clientregistry.api.event.PatientCreateUpdateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRegistryManager implements GlobalPropertyListener {
	
	private final AtomicBoolean isRunning = new AtomicBoolean(false);
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private DaemonToken daemonToken;
	
	@Autowired
	private PatientCreateUpdateListener patientListener;
	
	public void setDaemonToken(DaemonToken daemonToken) {
		this.daemonToken = daemonToken;
	}
	
	@Override
	public boolean supportsPropertyName(String propertyName) {
		return ClientRegistryConstants.GP_CLIENT_REGISTRY_SERVER_URL.equals(propertyName);
	}
	
	@Override
	public void globalPropertyChanged(GlobalProperty newValue) {
		try {
			log.trace(String.format("Notified of change to property: %s", newValue.getProperty()));
			if (StringUtils.isNotBlank((String) newValue.getValue())) {
				enableClientRegistry();
			} else {
				disableClientRegistry();
			}
		}
		catch (Exception e) {
			log.error("Failed to read property value, disabling client registry!");
			disableClientRegistry();
		}
	}
	
	@Override
	public void globalPropertyDeleted(String propertyName) {
		disableClientRegistry();
	}
	
	public void enableClientRegistry() {
		log.info("Enabling Client Registry!");
		
		if (daemonToken != null) {
			patientListener.setDaemonToken(daemonToken);
		}
		
		if (!isRunning.get()) {
			Event.subscribe(Patient.class, Event.Action.CREATED.toString(), patientListener);
			Event.subscribe(Patient.class, Event.Action.UPDATED.toString(), patientListener);
		}
		
		isRunning.set(true);
	}
	
	public void disableClientRegistry() {
		log.info("Disabling Client Registry!");
		
		if (isRunning.get()) {
			Event.unsubscribe(Patient.class, Event.Action.CREATED, patientListener);
			Event.unsubscribe(Patient.class, Event.Action.UPDATED, patientListener);
		}
		
		isRunning.set(false);
	}
}
