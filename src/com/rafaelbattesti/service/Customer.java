package com.rafaelbattesti.service;

import java.util.ArrayList;

public class Customer {
	
	//Customer attributes
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private boolean isDelivery;
	private ArrayList<Pizza> pizzaList;
	
	//Constructor
	public Customer(String newFirstName, String newLastName, String newPhone, String newAddress, String delivery) {
		firstName = newFirstName;
		lastName = newLastName;
		phone = newPhone;
		address = newAddress;
		pizzaList = new ArrayList<>();
		
		if (delivery.equals("delivery")) {
			isDelivery = true;
		} else if (delivery.equals("pickup")) {
			isDelivery = false;
		}
		
	}
	
	//What's the delivery method?
	public String getDeliveryMethod() {
		if (isDelivery) {
			return "Delivery";
		} else {
			return "Pickup";
		}
	}

	//Getters and Setters
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPhone() {return phone;}
	public String getAddress() {return address;}
	public ArrayList<Pizza> getPizzaList() {return pizzaList;}
	public boolean getIsDelivery() {return isDelivery;}
	public void setFirstName(String newFirstName) {firstName = newFirstName;}
	public void setLastName(String newLastName) {lastName = newLastName;}
	public void setPhone(String newPhone) {phone = newPhone;}
	public void setAddress(String address) {this.address = address;}
	public void addPizza(Pizza newPizza) {pizzaList.add(newPizza);}
}
