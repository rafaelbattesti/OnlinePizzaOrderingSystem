package com.rafaelbattesti.service;

/**
 * Models a Pizza object.
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public class Pizza implements PizzaInterface {
	
	/**
	 * Size of the pizza
	 */
	private String size;
	
	/**
	 * List of toppings
	 */
	private String[] toppingList;
	
	/**
	 * Qty of the same pizza
	 */
	private int qty;
	
	/**
	 * Delivery or takeout
	 */
	private boolean isDelivery;
	
	/**
	 * Number of toppings
	 */
	private int numToppings;
	
	/**
	 * Total price of the pizza applied discount.
	 */
	private double totalPizza;
	
	/**
	 * Constructs a pizza from the user input
	 * @param size of the pizza
	 * @param toppingList from checkboxes
	 * @param qty of pizzas of the same flavor
	 */
	public Pizza(String size, String[] toppingList, int qty) {
		this.size = size;
		this.toppingList = toppingList;
		this.qty = qty;
		this.numToppings = toppingList.length;
		calculateTotal();
	}
	
	/**
	 * Constructs a pizza from the Database information
	 * @param size of the pizza
	 * @param numToppings how many toppings
	 * @param qty of the same pizza
	 * @param isDelivery or takeout
	 * @param totalPizza total of the pizza
	 */
	public Pizza(String size, int numToppings, int qty, boolean isDelivery, double totalPizza) {
		this.size = size;
		this.numToppings = numToppings;
		this.qty = qty;
		this.isDelivery = isDelivery;
		this.totalPizza = totalPizza;
	}

	/**
	 * This calculates the total price of the pizza applied discounts
	 */
	private void calculateTotal() {
		
		if (numToppings < 4) {
			totalPizza += numToppings;
		} else if (numToppings == 8){
			totalPizza += numToppings - 2;
		} else {
			totalPizza += numToppings - 1;
		}
		
		if (size.equals("large")) {
			totalPizza += 7;
		} else {
			totalPizza += 5;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#toString()
	 */
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

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#getSize()
	 */
	@Override
	public String getSize() {
		return size;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#getToppingList()
	 */
	@Override
	public String[] getToppingList() {
		return toppingList;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#getQty()
	 */
	@Override
	public int getQty() {
		return qty;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#getNumToppings()
	 */
	@Override
	public int getNumToppings() {
		return numToppings;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#getIsDelivery()
	 */
	@Override
	public boolean getIsDelivery() {
		return isDelivery;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.PizzaInterface#getTotalPizza()
	 */
	@Override
	public double getTotalPizza() {
		return totalPizza;
	}
}
