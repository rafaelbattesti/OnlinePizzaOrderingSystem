package com.rafaelbattesti.service;

import java.util.Date;

public class Receipt {
	
	private Date date;
	private Order order;
	
	
	public double calculateTotal(){
		double total = 0;
		for (Pizza pizza : order.getPizzaList()) {
			total += pizza.calculateTotal();
		}
		return total;
	}
	
}
