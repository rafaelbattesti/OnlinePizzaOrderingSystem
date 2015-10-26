package com.rafaelbattesti.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Receipt {
	
	//Date Format for receipt
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	private static final String TIME_FORMAT = "HH:mm:ss";
	
	//Receipt attributes
	private String date;
	private String time;
	private Customer customer;
	
	//Sets Customer
	public void setCustomer(Customer newCustomer) {
		customer = newCustomer;
	}
	
	//Sets the date
	public void setDate() {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		date = df.format(new Date());
	}
	
	//Sets delivery time
	public void setTime() throws ParseException {
		
		DateFormat df = new SimpleDateFormat(TIME_FORMAT);
		String timeStr = df.format(new Date());
		Date datetime = df.parse(timeStr);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);
		
		if (customer.getIsDelivery()) {
			cal.add(Calendar.HOUR, 1);
		} else {
			cal.add(Calendar.HOUR, 2);
		}
			
		
		time = df.format(cal.getTime());
	}
	
	//Gets time
	public String getTime() {
		return time;
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
