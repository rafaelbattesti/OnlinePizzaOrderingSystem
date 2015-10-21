package com.rafaelbattesti.service;

import com.rafaelbattesti.data.DataAccess;

public class Manager {
	
	private DataAccess db;
	private String message;
	
	public Manager () {
		db = new DataAccess();
	}

	public boolean authenticate(String username, String password) {
		boolean isAuth = false;
		
		db.connect();
		isAuth = db.authenticate(username, password);
		message = db.getMessage();
		db.disconnect();
		
		return isAuth;
	}
	
	public String getDatabaseMessage () {
		return message;
	}

}
