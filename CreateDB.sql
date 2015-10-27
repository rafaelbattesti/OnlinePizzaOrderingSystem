CREATE DATABASE PizzaParlour;
USE PizzaParlour;

--Creates the table to hold the orders
CREATE TABLE OrderInfo (
	SizeOfPizza VARCHAR(10), 
	NumOfToppings INT,
	Quantity INT,
	Delivery BOOLEAN,
	Price DOUBLE);

--Creates the admin table for admin access and authentication
CREATE TABLE Admin (
	Username VARCHAR(10) NOT NULL PRIMARY KEY,
	Password VARCHAR(32) NOT NULL,
	LastLogin DATETIME);

--This password corresponds to the string "pass". Then to login to the administrative area, use admin/pass.
--Try some inexistent user or wrong password.
INSERT INTO Admin (Username, Password) VALUES ('admin', '1a1dc91c907325c69271ddf0c944bc72');