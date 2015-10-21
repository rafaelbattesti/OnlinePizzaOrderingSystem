package com.rafaelbattesti.service;

public class Customer {
	
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private Order order;
	
	public Customer(String newFirstName, String newLastName, String newPhone, String newAddress) {
		firstName = newFirstName;
		lastName = newLastName;
		phone = newPhone;
		address = newAddress;
	}

	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPhone() {return phone;}
	public String getAddress() {return address;}
	public Order getOrder() {return order;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setPhone(String phone) {this.phone = phone;}
	public void setAddress(String address) {this.address = address;}
	public void setOrder(Order order) {this.order = order;}
}
