package com.rafaelbattesti.service;

/**
 * Models a Pizza Interface for use by the View Layer.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public interface PizzaInterface {

	/**
	 * Overrides the toString method to construct a String for a Pizza object for the view layer
	 * @return
	 */
	String toString();

	/**
	 * @return size
	 */
	String getSize();

	/**
	 * @return list of toppings
	 */
	String[] getToppingList();

	/**
	 * @return qty of the same pizza
	 */
	int getQty();

	/**
	 * @return number of toppings
	 */
	int getNumToppings();

	/**
	 * @return delivery option
	 */
	boolean getIsDelivery();

	/**
	 * @return total value of the pizza applied discountss
	 */
	double getTotalPizza();

}