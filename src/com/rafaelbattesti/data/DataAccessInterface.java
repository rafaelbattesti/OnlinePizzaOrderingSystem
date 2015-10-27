package com.rafaelbattesti.data;

import java.util.ArrayList;

import com.rafaelbattesti.service.CustomerInterface;
import com.rafaelbattesti.service.Pizza;

/**
 * Models the interface to the model layer
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public interface DataAccessInterface {
	
	/**
	 * This connects to the DB
	 */
	void connect();

	/**
	 * This disconnects the DB
	 */
	void disconnect();

	/**
	 * This inserts orders at the DB
	 * @param customer holding a list of pizzas
	 */
	void insertOrders(CustomerInterface customer);

	/**
	 * This selects the pizzas from the DB
	 * @return pizzaList
	 */
	ArrayList<Pizza> selectDayOrders();

	/**
	 * This authenticates a user and updates the last login time and date
	 * @param username
	 * @param password
	 * @return true if authenticated and false for anything else
	 */
	boolean authenticate(String username, String password);

	/**
	 * Returns the message to upper layers
	 * @return message
	 */
	String getMessage();

	/**
	 * Returns the error log
	 * @return
	 */
	String getLog();

}