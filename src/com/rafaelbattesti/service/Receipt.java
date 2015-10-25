package com.rafaelbattesti.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
	
	//Date Format for receipt
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	//Receipt attributes
	private Date date;
	private Customer customer;
	
	//Sets the date
	public String setDate() {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date());
	}
	
	//Calculates the total of the receipt
	public double calculateTotal(){
		double total = 0;
		for (Pizza pizza : customer.getPizzaList()) {
			total += pizza.calculateTotal();
		}
		return total;
	}
}
