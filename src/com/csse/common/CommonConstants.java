package com.csse.common;

import java.text.MessageFormat;

public final class CommonConstants {
	
	public static class ConfigUtil{
		public static final String FILEPATH_STRING =  "../config/config.properties";
	}
	
	public static class QueryUtil{
		public static final String EMPLOYEE_QUERY_PATH_STRING =  "src/com/csse/config/EmployeeQuery.xml";
	}
	
	public static class TransformUtil{
		public static final String BASE_PATH = "//Employees/Employee[";
		
		
		
		public static String  getCompliePath(int number,String path2) {
			String message = MessageFormat.format("{0}" + "{1}"+ "{2}",BASE_PATH, number, path2);
			return message;
		}
	}
	
	public static class EmployeeServices{
		
	}
    
}
