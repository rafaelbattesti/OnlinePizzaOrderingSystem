package com.rafaelbattesti.service;

public class Pizza {
	
	private String size;
	private String[] toppingList;
	private int qty;
	private boolean isDelivery;
	private int numToppings;
	private double totalPizza;
	
	public Pizza(String newSize, String[] newToppingList, int newQty) {
		size = newSize;
		toppingList = newToppingList;
		qty = newQty;
		numToppings = newToppingList.length;
	}
	
	public Pizza(String newSize, int newNumToppings, int newQty, boolean newIsDelivery, double total) {
		size = newSize;
		numToppings = newNumToppings;
		qty = newQty;
		isDelivery = newIsDelivery;
		totalPizza = total;
	}

	public double calculateTotal() {
		double total = 0.0;
		
		if (numToppings < 4) {
			total += numToppings;
		} else if (numToppings == 8){
			total += numToppings - 2;
		} else {
			total += numToppings - 1;
		}
		
		if (size.equals("large")) {
			total += 7;
		} else {
			total += 5;
		}
		return total;
	}
	
	@Override
	public String toString() {
		String str = size + " pizza (";
		
		for (int i = 0; i < numToppings; i++) {
			if (i != numToppings - 1) {
				str += toppingList[i] + ", ";
			} else {
				str += toppingList[i] + ")";
			}
		}
		return str;
	}

	public String getSize() {return size;}
	public String[] getToppingList() {return toppingList;}
	public int getQty() {return qty;}
	public int getNumToppings() {return numToppings;}
	public void setNumToppings(int numToppings) {this.numToppings = numToppings;}
	public boolean getIsDelivery() {return isDelivery;}
	public void setDelivery(boolean isDelivery) {this.isDelivery = isDelivery;}
	public double getTotalPizza() {return totalPizza;}
	public void setTotalPizza(double totalPizza) {this.totalPizza = totalPizza;}
	
}
