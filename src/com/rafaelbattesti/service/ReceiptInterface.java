package com.rafaelbattesti.service;

import java.text.ParseException;

/**
 * Models the interface for the View Layer.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public interface ReceiptInterface {

	/**
	 * Sets the time for customer delivery or pickup
	 * @param customer containing a delivery option
	 * @throws ParseException if java cannot parse the time
	 */
	void setTime(CustomerInterface customer) throws ParseException;

	/**
	 * @return time of delivery/pickup
	 */
	String getTime();

	/**
	 * Calculates the total value of a customer order
	 * @param customer containing a list of pizzas
	 * @return total in dollars
	 */
	double calculateTotal(CustomerInterface customer);

}