package com.rafaelbattesti.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.rafaelbattesti.service.Customer;
import com.rafaelbattesti.service.Pizza;

public class DataAccess {
	
	//JDBC and DB credentials
	private static final String JDBC_DRIVER       = "com.mysql.jdbc.Driver";
	private static final String DB_URL            = "jdbc:mysql://localhost/PizzaParlour";
	private static final String DB_USER           = "root";
	private static final String DB_PASS           = "1111";
	
	//Error Messages
	private static final String ERR_DRIVER         = "JDBC Driver not found";
	private static final String ERR_CONNECT        = "Could not connect to database";
	private static final String ERR_DISCONNECT     = "Could not disconnect from database";
	private static final String ERR_PREPARE        = "Could not prepare statements for SQL";
	private static final String ERR_INSERT         = "Could not insert orders into database";
	private static final String ERR_READING        = "Could not read from database";
	private static final String ERR_RETR_USER      = "Could not retrieve user by username";
	private static final String ERR_CLOSE_RESULT   = "Could not close the result resource";
	private static final String ERR_MD5            = "Could not find MD5 algorithm";
	private static final String ERR_USER_NOT_FOUND = "Sorry, user not found";
	private static final String ERR_WRONG_PASSWD   = "Sorry, wrong password";
	
	//Success Messages
	private static final String SUC_PREPARE        = "SQL statements successfuly prepared";
	private static final String SUC_CONNECT        = "Database successfuly connected";
	private static final String SUC_DISCONNECT     = "Database successfuly disconnected";
	private static final String SUC_INSERT         = "Orders successfully inserted!";
	
	
	//Other constants
	private static final String DATE_FORMAT        = "yyyy/MM/dd HH:mm:ss";
	
	//Data Fields
	private Connection conn;
	private String     log;
	private String     message;

	//Prepared Statements
	private PreparedStatement authenticatePstm;
	private PreparedStatement updateLastLoginPstm;
	private PreparedStatement insertOrdersPstm;
	private PreparedStatement selectOrdersPstm;
	

	private void prepareAllStatements () {
		
		clearMessages();
		
		//SQL statements
		String authenticateSql    = "SELECT Username, Password FROM Admin WHERE Username = ?;";
		String updateLastLoginSql = "UPDATE Admin SET LastLogin = ? WHERE Username = ?;";
		String insertOrdersSql    = "INSERT INTO OrderInfo (SizeOfPizza, NumOfToppings, Quantity, Delivery, Price)"
										+ "VALUES (?, ?, ?, ?, ?);";
		String selectOrdersSql    = "SELECT * FROM OrderInfo;";
		
		try {
			
			//Prepare statements and assign message
			authenticatePstm    = conn.prepareStatement(authenticateSql);
			updateLastLoginPstm = conn.prepareStatement(updateLastLoginSql);
			insertOrdersPstm    = conn.prepareStatement(insertOrdersSql);
			selectOrdersPstm    = conn.prepareStatement(selectOrdersSql);
			
			
			message             = SUC_PREPARE;
			
		} catch (SQLException e) {
			log = logError(e, ERR_PREPARE);
		}
	}
	
	public void connect() {
		
		clearMessages();
		
		try {
			
			//Initializes JDBC Driver
			Class.forName(JDBC_DRIVER);
			
			//Creates a connection and assigns message
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			message = SUC_CONNECT;
			
		} catch (ClassNotFoundException e) {
			log = logError(e, ERR_DRIVER);
		} catch (SQLException e) {
			log = logError(e, ERR_CONNECT);
		}
		
		// Prepare all statements if a connection is established
		if (conn != null) {
			prepareAllStatements();
		}
		
	}
	
	public void disconnect() {
		
		clearMessages();
		
		try {
			if (conn != null) {
				//Closes connection and assigns message
				conn.close();
				message = SUC_DISCONNECT;
			}	
		} catch (SQLException e) {	
			log = logError(e, ERR_DISCONNECT);
		}
	}
	
	public void insertOrders(Customer customer) {
		clearMessages();
		ArrayList<Pizza> pizzaList = customer.getPizzaList();
		int affectedRows = 0;
		
		try {
			
			for (Pizza pizza : pizzaList) {
				insertOrdersPstm.setString(1, pizza.getSize());
				insertOrdersPstm.setInt(2, pizza.getToppingList().length);
				insertOrdersPstm.setInt(3, pizza.getQty());
				insertOrdersPstm.setBoolean(4, customer.getIsDelivery());
				insertOrdersPstm.setDouble(5, pizza.calculateTotal());
				
				affectedRows = insertOrdersPstm.executeUpdate();
				
				// Updates message
				if (affectedRows != 0) {
					message = SUC_INSERT;
				}
			}
			
		} catch (SQLException e) {
			log = logError(e, ERR_INSERT);
		}
	}
	
	public ArrayList<Pizza> selectDayOrders() {
		
		ResultSet result = null;
		ArrayList<Pizza> pizzaList = new ArrayList<>();
		
		try {
			
			result = selectOrdersPstm.executeQuery();
			
			while(result.next()) {
				String num = result.getString("SizeOfPizza");
				int top = result.getInt("NumOfToppings");
				int qty = result.getInt("Quantity");
				boolean del = result.getBoolean("Delivery");
				double total = result.getDouble("Price");
				Pizza pizza = new Pizza(num, top, qty, del, total);
				pizzaList.add(pizza);
			}
			
		} catch (Exception e) {
			log = logError(e, ERR_READING);
		} finally {
			if (result != null) {
				
				try {
					result.close();
				} catch (SQLException e) {
					log = logError(e, ERR_CLOSE_RESULT);
				}
				
			}
		}
		
		return pizzaList;
	}

	public boolean authenticate(String username, String password) {
		
		clearMessages();
		boolean isAuth = false;
		password = MD5(password);
		ResultSet result = null;
		
		try {
			
			authenticatePstm.setString(1, username);
			result = authenticatePstm.executeQuery();
			
			if (result.next()) {
				if (result.getString("password").equals(password)) {
					updateLastLoginPstm.setString(1, dateFormatter());
					updateLastLoginPstm.setString(2, username);
					updateLastLoginPstm.executeUpdate();
					isAuth = true;
				} else {
					message = ERR_WRONG_PASSWD;
					isAuth = false;
				}
			} else {
				message = ERR_USER_NOT_FOUND;
			}	
		} catch (SQLException e) {
			log = logError(e, ERR_RETR_USER);
		} finally {
			try {
				if (result != null) {
					result.close();
				}
			} catch (SQLException e) {
				log = logError(e, ERR_CLOSE_RESULT);
			}
		}
		return isAuth;
	}
	
	private String MD5 (String password) {
		
		StringBuffer buffer = new StringBuffer();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passBites = md.digest(password.getBytes());

	        for (int i = 0; i < passBites.length; ++i) {
	            buffer.append(Integer.toHexString((passBites[i] & 0xFF) | 0x100).substring(1,3));
	         }
	        
		} catch (NoSuchAlgorithmException e) {
			log = logError(e, ERR_MD5);
		}
		
		return buffer.toString();
		
	}
	
	private String logError (Exception e, String errMessage) {
		
		String date = dateFormatter();
		
		//Builds different logs for different Exceptions
		if (e instanceof SQLException) {
			SQLException ex = (SQLException) e;
			return date + "\n" + errMessage + ":" + ex.getSQLState() + ":" + ex.getErrorCode();
		} else {
			return date + "\n" + errMessage;
		}
	}
	
	private String dateFormatter () {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date());
	}

	private void clearMessages () {
		log = message = null;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getLog () {
		return log;
	}
}
