package com.rafaelbattesti.service;

import java.util.ArrayList;

public class Pizza {
	
	private String size;
	private ArrayList<Topping> toppingList;
	private double total;
	
	public Pizza(String newSize) {
		size = newSize;
		toppingList = new ArrayList<>();
	}
	
	public void addTopping(Topping newTopping) {
		toppingList.add(newTopping);
	}
	
	public double calculateTotal() {
		return 0.0;
	}
}
