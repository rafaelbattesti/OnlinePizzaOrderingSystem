package com.rafaelbattesti.service;

import java.util.ArrayList;

/**
 * Models a Manager Interface for use by the View Layer.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public interface ManagerInterface {

	/**
	 * Authenticates a user for administrative login
	 * @param user
	 * @return user whether authenticated or not
	 */
	User authenticate(UserInterface user);

	/**
	 * Adds an order to the DB
	 * @param customer
	 */
	void addOrder(CustomerInterface customer);

	/**
	 * Queries DB for orders
	 * @return pizzaList
	 */
	ArrayList<Pizza> daySummary();

	/**
	 * @return message from the database object for the view layer.
	 */
	String getDatabaseMessage();

	/**
	 * Calculates the total for a given list of pizzas
	 * @param pizzaList
	 * @return total in dollars
	 */
	double calculateTotal(ArrayList<Pizza> pizzaList);

}