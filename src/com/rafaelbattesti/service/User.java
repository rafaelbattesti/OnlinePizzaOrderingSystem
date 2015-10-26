package com.rafaelbattesti.service;

public class User {
	
	private String username;
	private String password;
	private boolean isAuth;
	private String message;
	
	public User (String newUsername, String newPassword) {
		username = newUsername;
		password = newPassword;
	}
	
	public void setAuth(boolean newAuth) {
		isAuth = newAuth;
	}
	
	public boolean getAuth() {
		return isAuth;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String newMessage) {
		message = newMessage;
	}

	public String getUsername() {
		return username;
	}
}
