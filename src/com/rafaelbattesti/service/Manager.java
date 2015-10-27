package com.rafaelbattesti.service;

import java.util.ArrayList;

import com.rafaelbattesti.data.DataAccess;
import com.rafaelbattesti.data.DataAccessInterface;

/**
 * Models a Manager to manage access to database.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public class Manager implements ManagerInterface {
	
	/**
	 * Access to database
	 */
	private DataAccessInterface db;
	
	/**
	 * Message to the view
	 */
	private String message;
	
	/**
	 * Constructs a DB
	 */
	public Manager () {
		db = new DataAccess();
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ManagerInterface#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public User authenticate(UserInterface user) {
		
		db.connect();
		user.setAuth(db.authenticate(user.getUsername(), user.getPassword()));
		user.setMessage(db.getMessage());
		db.disconnect();
		
		return (User) user;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ManagerInterface#addOrder(com.rafaelbattesti.service.CustomerInterface)
	 */
	@Override
	public void addOrder(CustomerInterface customer) {
		db.connect();
		db.insertOrders(customer);
		message = db.getMessage();
		db.disconnect();
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ManagerInterface#daySummary()
	 */
	@Override
	public ArrayList<Pizza> daySummary() {
		db.connect();
		ArrayList<Pizza> pizzaList = db.selectDayOrders();
		message = db.getMessage();
		db.disconnect();
		return pizzaList;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ManagerInterface#getDatabaseMessage()
	 */
	@Override
	public String getDatabaseMessage () {
		return message;
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ManagerInterface#calculateTotal(java.util.ArrayList)
	 */
	@Override
	public double calculateTotal(ArrayList<Pizza> pizzaList) {
		double total = 0.0;
		for (PizzaInterface pizza : pizzaList) {
			total += pizza.getTotalPizza();
		}
		return total;
	}
}
