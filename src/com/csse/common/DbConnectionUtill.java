package com.csse.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 * This class is used to establish Java mysql database connectivity
 *  
 *
 */

public class DbConnectionUtill extends ConfigUtil{

	private static Connection connection = null;
	private static String urlString = properties.getProperty("url");
	private static String userNameString = properties.getProperty("username");
	private static String passwordString = properties.getProperty("password");
	private static String driverNameString = properties.getProperty("driverName");

	public static final Logger log = Logger.getLogger(DbConnectionUtill.class.getName());
	
	/**
	 * This provides the connection for JDBC connectivity 
	 *@return Connection type instance
	 *
	 */
	
	public static Connection getConnection() {
		//check if connection exist
		if (connection == null) {
			try {
				
				//get connection using JDBC driver
				Class.forName(driverNameString);
				connection = DriverManager.getConnection(urlString, userNameString,passwordString);
				System.out.println(connection);
				return connection;
			}
			
			catch (ClassNotFoundException  e) {
				log.log(Level.SEVERE,e.getMessage());
			}catch (SQLTimeoutException  e) {
				log.log(Level.SEVERE,e.getMessage());
			}catch (SQLException  e) {
				log.log(Level.SEVERE,e.getMessage());
			}catch (ExceptionInInitializerError  e) {
				log.log(Level.SEVERE,e.getMessage());
			}catch (LinkageError   e) {
				log.log(Level.SEVERE,e.getMessage());
			}catch (Exception e) {
				log.log(Level.SEVERE,e.getMessage());
			
			}finally {
				try {
					
					//if exception occurs, this will close the database connection 
					
					if(connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE,e.getMessage());
				}
			}
					
	}
		return connection;
	}
}
