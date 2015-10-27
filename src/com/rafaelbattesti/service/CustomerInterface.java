package com.rafaelbattesti.service;

import java.util.ArrayList;

/**
 * Models an Interface for the Customer object for use at the View Layer.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public interface CustomerInterface {

	/**
	 * @return a string equivalent to delivery option
	 */
	String getDeliveryString();

	/**
	 * @return firstName
	 */
	String getFirstName();

	/**
	 * @return lastName
	 */
	String getLastName();

	/**
	 * @return phone
	 */
	String getPhone();

	/**
	 * @return address
	 */
	String getAddress();

	/**
	 * @return pizzaList
	 */
	ArrayList<Pizza> getPizzaList();

	/**
	 * Validates and sets firstName
	 * @param firstName
	 * @throws IllegalArgumentException if argument does not pass validation rule
	 */
	void setFirstName(String firstName) throws IllegalArgumentException;

	/**
	 * Validates and sets lastName
	 * @param lastName
	 * @throws IllegalArgumentException if argument does not pass validation rule
	 */
	void setLastName(String lastName) throws IllegalArgumentException;

	/**
	 * Validates and sets phone
	 * @param phone
	 * @throws IllegalArgumentException if argument does not pass validation rule
	 */
	void setPhone(String phone) throws IllegalArgumentException;

	/**
	 * Validates and sets address
	 * @param address
	 * @throws IllegalArgumentException if argument does not pass validation rule
	 */
	void setAddress(String address) throws IllegalArgumentException;

	/**
	 * @return raw delivery boolean
	 */
	boolean getDelivery();

	/**
	 * @param pizza to add to list
	 */
	void addPizza(Pizza pizza);

}