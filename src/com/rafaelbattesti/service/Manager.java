package com.rafaelbattesti.service;

import java.util.ArrayList;

import com.rafaelbattesti.data.DataAccess;

public class Manager {
	
	private DataAccess db;
	private String message;
	private ArrayList<Pizza> pizzaList;
	
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
	
	public void addOrder(Customer customer) {
		db.connect();
		db.insertOrders(customer);
		message = db.getMessage();
		db.disconnect();
	}
	
	public ArrayList<Pizza> daySummary() {
		db.connect();
		pizzaList = db.selectDayOrders();
		message = db.getMessage();
		db.disconnect();
		return pizzaList;
	}
	
	public String getDatabaseMessage () {
		return message;
	}

	public double calculateTotal() {
		double total = 0.0;
		for (Pizza pizza : pizzaList) {
			total += pizza.getTotalPizza();
		}
		return total;
	}

}
