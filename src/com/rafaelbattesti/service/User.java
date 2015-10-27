package com.rafaelbattesti.service;

/**
 * Models a Admin User
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public class User implements UserInterface {
	
	/**
	 * Username
	 */
	private String username;
	
	/**
	 * User Password
	 */
	private String password;
	
	/**
	 * Holds authentication status for the user
	 */
	private boolean isAuth;
	
	/**
	 * Holds the message for the View Layer (user not found, wrong password)
	 */
	private String message;
	
	/**
	 * Constructs a user for authentication
	 * @param username
	 * @param password
	 */
	public User (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.UserInterface#setAuth(boolean)
	 */
	@Override
	public void setAuth(boolean newAuth) {
		isAuth = newAuth;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.UserInterface#getAuth()
	 */
	@Override
	public boolean getAuth() {
		return isAuth;
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.UserInterface#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.UserInterface#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String newMessage) {
		message = newMessage;
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.UserInterface#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.UserInterface#getPassword()
	 */
	public String getPassword() {
		return password;
	}
}
