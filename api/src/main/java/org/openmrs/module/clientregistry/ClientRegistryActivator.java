/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.clientregistry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.DaemonToken;
import org.openmrs.module.DaemonTokenAware;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.clientregistry.api.ClientRegistryManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */

@Component
public class ClientRegistryActivator extends BaseModuleActivator implements ApplicationContextAware, DaemonTokenAware {
	
	private static ApplicationContext applicationContext;
	
	private static DaemonToken daemonToken;
	
	// Log
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ClientRegistryManager clientRegistryManager;
	
	@Autowired
	private ClientRegistryConfig config;
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting Client Registry Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		try {
			applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
			
			if (daemonToken != null) {
				clientRegistryManager.setDaemonToken(daemonToken);
			}
			
			if (config.clientRegistryConnectionEnabled()) {
				clientRegistryManager.enableClientRegistry();
			}
			
			log.info("Client Registry Module started");
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping Client Registry Module");
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		if (clientRegistryManager != null) {
			clientRegistryManager.disableClientRegistry();
		}
		log.info("Client Registry Module stopped");
	}
	
	@Override
	public void setDaemonToken(DaemonToken token) {
		this.daemonToken = token;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
}
