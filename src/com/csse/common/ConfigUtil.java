package com.csse.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;


public class ConfigUtil {

	//Create properties instance 
	public static final Properties properties= new Properties();
	
	//Create logger instance 
	public static final Logger log = Logger.getLogger(ConfigUtil.class.getName());
	
	static {
		try {
			
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.ConfigUtil.FILEPATH_STRING));
			
		} catch (IOException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch(IllegalArgumentException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch(NullPointerException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch(Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}
}
