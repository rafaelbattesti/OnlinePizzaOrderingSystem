package com.rafaelbattesti.service;

import java.util.ArrayList;

public class Order {
	
	private ArrayList<Pizza> pizzaList;
	
	public Order() {
		pizzaList = new ArrayList<>();
	}
	
	public void addPizza(Pizza newPizza) {
		pizzaList.add(newPizza);
	}
	
	public ArrayList<Pizza> getPizzaList() {
		return pizzaList;
	}

}
