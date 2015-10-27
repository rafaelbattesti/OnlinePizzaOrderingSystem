package com.rafaelbattesti.service;

/**
 * Models a User interface for use in the view layer.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public interface UserInterface {

	/**
	 * Sets the attribute to the user
	 * @param newAuth
	 */
	void setAuth(boolean newAuth);
	
	/**
	 * @return if user is authenticated
	 */
	boolean getAuth();

	/**
	 * @return message from authentication
	 */
	String getMessage();

	/**
	 * @param newMessage from the manager after authentication
	 */
	void setMessage(String newMessage);

	/**
	 * @return username
	 */
	String getUsername();
	
	/**
	 * @return password
	 */
	String getPassword();

}