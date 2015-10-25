package com.rafaelbattesti.service;

import java.util.ArrayList;

public class Pizza {
	
	//Pizza attributes
	private String size;
	private String[] toppingList;
	private String isDelivery;
	
	//Pizza constructor
	public Pizza(String newSize, String[] newToppingList, String delivery) {
		size = newSize;
		toppingList = newToppingList;
		isDelivery = delivery;
	} 
	
	//Calculates the pizza total
	public double calculateTotal() {
		double total = 0.0;
		total += toppingList.length;
		if (size.equals("large")) {
			total += 7;
		} else {
			total += 5;
		}
		return total;
	}
	
	//Override toString method
	@Override
	public String toString() {
		String str = size + " pizza (" + isDelivery + ": ";
		
		for (int i = 0; i < toppingList.length; i++) {
			if (i != toppingList.length - 1) {
				str += toppingList[i] + ", ";
			} else {
				str += toppingList[i] + ")";
			}
		}
		return str;
	}

	//Getters and setters
	public String getSize() {return size;}
	public String[] getToppingList() {return toppingList;}
	public String getDelivery() {return isDelivery;}
}
