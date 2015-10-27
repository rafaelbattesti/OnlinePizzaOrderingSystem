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

import com.rafaelbattesti.service.CustomerInterface;
import com.rafaelbattesti.service.Pizza;
import com.rafaelbattesti.service.PizzaInterface;

/**
 * Models a database object for connection and access.
 * @author rafaelbattesti
 * @since 2015.10.20
 *
 */
public class DataAccess implements DataAccessInterface {
	
	/**
	 * JDBC Driver
	 */
	private static final String JDBC_DRIVER       = "com.mysql.jdbc.Driver";
	
	/**
	 * DB URL
	 */
	private static final String DB_URL            = "jdbc:mysql://localhost/PizzaParlour";
	
	/**
	 * DB Username
	 */
	private static final String DB_USER           = "root";
	
	/**
	 * DB Password
	 */
	private static final String DB_PASS           = "1111";
	
	/**
	 * Error finding Driver
	 */
	private static final String ERR_DRIVER         = "JDBC Driver not found";
	
	/**
	 * Error connecting to DB
	 */
	private static final String ERR_CONNECT        = "Could not connect to database";
	
	/**
	 * Error disconnecting from DB 
	 */
	private static final String ERR_DISCONNECT     = "Could not disconnect from database";
	
	/**
	 * Error preparing statements
	 */
	private static final String ERR_PREPARE        = "Could not prepare statements for SQL";
	
	/**
	 * Error inserting orders into DB
	 */
	private static final String ERR_INSERT         = "Could not insert orders into database";
	
	/**
	 * Error reading orders form DB
	 */
	private static final String ERR_READING        = "Could not read from database";
	
	/**
	 * Error retrieving user information from DB
	 */
	private static final String ERR_RETR_USER      = "Could not retrieve user by username";
	
	/**
	 * Error closing result resources
	 */
	private static final String ERR_CLOSE_RESULT   = "Could not close the result resource";
	
	/**
	 * Error finding MD5 algorithm
	 */
	private static final String ERR_MD5            = "Could not find MD5 algorithm";
	
	/**
	 * Error user not found
	 */
	private static final String ERR_USER_NOT_FOUND = "Sorry, user not found";
	
	/**
	 * Error wrong password
	 */
	private static final String ERR_WRONG_PASSWD   = "Sorry, wrong password";
	
	/**
	 * Success preparing statements
	 */
	private static final String SUC_PREPARE        = "SQL statements successfuly prepared";
	
	/**
	 * Success connecting to DB
	 */
	private static final String SUC_CONNECT        = "Database successfuly connected";
	
	/**
	 * Success disconnecting from DB
	 */
	private static final String SUC_DISCONNECT     = "Database successfuly disconnected";
	
	/**
	 * Success inserting into DB
	 */
	private static final String SUC_INSERT         = "Orders successfully inserted!";
	
	/**
	 * Date format
	 */
	private static final String DATE_FORMAT        = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * Connection to DB
	 */
	private Connection conn;
	
	/**
	 * Error log
	 */
	private String log;
	
	/**
	 * Message to upper layers
	 */
	private String message;

	/**
	 * Prepared SQL for admin authentication
	 */
	private PreparedStatement authenticatePstm;
	
	/**
	 * Prepared SQL for Updating last login
	 */
	private PreparedStatement updateLastLoginPstm;
	
	/**
	 * Prepared SQL for inserting orders into DB
	 */
	private PreparedStatement insertOrdersPstm;
	
	/**
	 * Prepared SQL for retrieving orders from DB
	 */
	private PreparedStatement selectOrdersPstm;
	
	/**
	 * This prepares all statements on connection
	 */
	private void prepareAllStatements () {
		
		clearMessages();
		
		//SQL statements
		String authenticateSql    = "SELECT Username, Password FROM Admin WHERE Username = ?;";
		String updateLastLoginSql = "UPDATE Admin SET LastLogin = ? WHERE Username = ?;";
		String insertOrdersSql    = "INSERT INTO OrderInfo (SizeOfPizza, NumOfToppings, Quantity, Delivery, Price)"
										+ "VALUES (?, ?, ?, ?, ?);";
		String selectOrdersSql    = "SELECT * FROM OrderInfo;";
		
		try {
			
			//Prepare statements and updates message
			authenticatePstm    = conn.prepareStatement(authenticateSql);
			updateLastLoginPstm = conn.prepareStatement(updateLastLoginSql);
			insertOrdersPstm    = conn.prepareStatement(insertOrdersSql);
			selectOrdersPstm    = conn.prepareStatement(selectOrdersSql);
			message             = SUC_PREPARE;
			
		} catch (SQLException e) {
			log = logError(e, ERR_PREPARE);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#connect()
	 */
	@Override
	public void connect() {
		
		clearMessages();
		
		try {
			
			//Initializes JDBC Driver, create connection and updates message
			Class.forName(JDBC_DRIVER);
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
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#disconnect()
	 */
	@Override
	public void disconnect() {
		
		clearMessages();
		
		try {
			if (conn != null) {
				conn.close();
				message = SUC_DISCONNECT;
			}	
		} catch (SQLException e) {	
			log = logError(e, ERR_DISCONNECT);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#insertOrders(com.rafaelbattesti.service.Customer)
	 */
	@Override
	public void insertOrders(CustomerInterface customer) {
		
		clearMessages();
		ArrayList<Pizza> pizzaList = customer.getPizzaList();
		int affectedRows = 0;
		
		try {
			
			for (PizzaInterface pizza : pizzaList) {
				insertOrdersPstm.setString(1, pizza.getSize());
				insertOrdersPstm.setInt(2, pizza.getToppingList().length);
				insertOrdersPstm.setInt(3, pizza.getQty());
				insertOrdersPstm.setBoolean(4, customer.getDelivery());
				insertOrdersPstm.setDouble(5, pizza.getTotalPizza());
				
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
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#selectDayOrders()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
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
	
	/**
	 * This hashes the user password to compare with the hash stored in the DB.
	 * @param password ASCII password
	 * @return MD5 password
	 */
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
	
	/**
	 * This logs the error
	 * @param e exception thrown
	 * @param errMessage message to update log
	 * @return composed log with date and time of occurence
	 */
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
	
	/**
	 * Formats a date and returns the string date
	 * @return
	 */
	private String dateFormatter () {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date());
	}

	/**
	 * Clears the log and message before proceeding to DB operations
	 */
	private void clearMessages () {
		log = message = null;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
	/* (non-Javadoc)
	 * @see com.rafaelbattesti.data.DataAccessInterface#getLog()
	 */
	@Override
	public String getLog () {
		return log;
	}
}
