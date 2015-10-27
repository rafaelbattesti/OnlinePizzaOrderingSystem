package com.rafaelbattesti.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Models a Receipt object
 * @author rafaelbattesti
 * @since 2015.10.25
 */
public class Receipt implements ReceiptInterface {
	
	/**
	 * Date format
	 */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * Hour format for delivery/pickup times
	 */
	private static final String TIME_FORMAT = "HH:mm:ss";
	
	/**
	 * Date/time of the order
	 */
	private String date;
	
	/**
	 * Time for delivery/pickup
	 */
	private String time;
	
	/**
	 * Constructs a receipt with date/time
	 */
	public Receipt () {
		setDate();
	}
	
	/**
	 * Sets the date of the order
	 */
	private void setDate() {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		date = df.format(new Date());
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ReceiptInterface#setTime(com.rafaelbattesti.service.CustomerInterface)
	 */
	@Override
	public void setTime(CustomerInterface customer) throws ParseException {
		
		DateFormat df = new SimpleDateFormat(TIME_FORMAT);
		String timeStr = df.format(new Date());
		Date datetime = df.parse(timeStr);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);
		
		if (customer.getDelivery()) {
			cal.add(Calendar.HOUR, 1);
		} else {
			cal.add(Calendar.HOUR, 2);
		}
		time = df.format(cal.getTime());
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ReceiptInterface#getTime()
	 */
	@Override
	public String getTime() {
		return time;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.service.ReceiptInterface#calculateTotal(com.rafaelbattesti.service.CustomerInterface)
	 */
	@Override
	public double calculateTotal(CustomerInterface customer){
		double total = 0;
		for (PizzaInterface pizza : customer.getPizzaList()) {
			total += pizza.getTotalPizza();
		}
		return total;
	}
}
