package org.openmrs.module.clientregistry.api.event;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Daemon;
import org.openmrs.event.EventListener;
import org.openmrs.module.DaemonToken;
import org.springframework.stereotype.Component;

@Component
public class PatientCreateUpdateListener implements EventListener {
	
	public DaemonToken getDaemonToken() {
		return daemonToken;
	}
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public void setDaemonToken(DaemonToken daemonToken) {
		this.daemonToken = daemonToken;
	}
	
	private DaemonToken daemonToken;
	
	@Override
	public void onMessage(Message message) {
		log.trace("Received message: " + message);

		Daemon.runInDaemonThread(() -> {
			try {
				processMessage(message);
			}
			catch (Exception e) {
				log.error("Failed to update the user's last viewed patients property", e);
			}
		}, daemonToken);
	}
	
	private void processMessage(Message message) {
	}
	
}
