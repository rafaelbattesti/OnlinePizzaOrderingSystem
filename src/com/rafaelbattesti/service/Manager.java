package com.rafaelbattesti.service;

import com.rafaelbattesti.data.DataAccess;

public class Manager {

	public boolean authenticate(String username, String password) {
		
		DataAccess db = new DataAccess();
		boolean isAuth = false;
		
		db.connect();
		isAuth = db.authenticate(username, password);
		db.disconnect();
		
		return isAuth;
	}

}
