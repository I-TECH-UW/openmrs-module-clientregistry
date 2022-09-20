package org.openmrs.module.clientregistry.exception;

/**
 * Health information exchange communication base exception
 * 
 * @author Justin
 */

public class ClientRegistryException extends Exception {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * HIE Exception
	 */
	public ClientRegistryException() {
		
	}
	
	/**
	 * Creates a new HIE exception
	 */
	public ClientRegistryException(Exception cause) {
		super(cause);
	}
	
	/**
	 * Create health information exception
	 * 
	 * @param message
	 */
	public ClientRegistryException(String message) {
		super(message);
	}
	
	/**
	 * Create HIE Exception with cause
	 */
	public ClientRegistryException(String message, Exception e) {
		super(message, e);
	}
	
}
