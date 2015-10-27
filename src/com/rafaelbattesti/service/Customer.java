package com.rafaelbattesti.service;

import java.util.ArrayList;

/**
 * Models a Customer
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public class Customer implements CustomerInterface {
	
	/**
	 * Customer name
	 */
	private String firstName;
	
	/**
	 * Customer last name
	 */
	private String lastName;
	
	/**
	 * Customer phone
	 */
	private String phone;
	
	/**
	 * Customer address
	 */
	private String address;
	
	/**
	 * Delivery or pickup
	 */
	private boolean isDelivery;
	
	/**
	 * List of pizzas
	 */
	private ArrayList<Pizza> pizzaList;

	/**
	 * This constructs a Customer
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param address
	 * @param delivery
	 * @throws IllegalArgumentException in case arguments do not validate when submitted
	 */
	public Customer(String firstName, String lastName, String phone, String address, String delivery) throws IllegalArgumentException {
		
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAddress(address);
		pizzaList = new ArrayList<>();
		
		if (delivery.equals("delivery")) {
			isDelivery = true;
		} else if (delivery.equals("pickup")) {
			isDelivery = false;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getDeliveryString()
	 */
	@Override
	public String getDeliveryString() {
		if (isDelivery) {
			return "Delivery";
		} else {
			return "Pickup";
		}
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getPhone()
	 */
	@Override
	public String getPhone() {
		return phone;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getAddress()
	 */
	@Override
	public String getAddress() {
		return address;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getPizzaList()
	 */
	@Override
	public ArrayList<Pizza> getPizzaList() {
		return pizzaList;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (validate(firstName)) {
			this.firstName = firstName;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) throws IllegalArgumentException {
		if (validate(lastName)) {
			this.lastName = lastName;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#setPhone(java.lang.String)
	 */
	@Override
	public void setPhone(String phone) throws IllegalArgumentException {
		if (validate(phone)) {
			this.phone = phone;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#setAddress(java.lang.String)
	 */
	@Override
	public void setAddress(String address) throws IllegalArgumentException {
		if (validate(address)) {
			this.address = address;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#getDelivery()
	 */
	@Override
	public boolean getDelivery() {
		return isDelivery;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.CustomerInterface#addPizza(com.rafaelbattesti.service.Pizza)
	 */
	@Override
	public void addPizza(Pizza pizza) {
		pizzaList.add(pizza);
	}
	
	/**
	 * Validates a string for emptiness
	 * @param value string from user
	 * @return true for validated
	 */
	private boolean validate (String value) {
		if (value.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
