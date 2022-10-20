package com.csse.main;



import java.util.logging.Level;
import java.util.logging.Logger;

import com.csse.common.TransformUtil;
import com.csse.service.EmployeeServices;

public class ExecuteMain {
	
	private static final Logger log = Logger.getLogger(ExecuteMain.class.getName());
	
	public static void main(String[] args) {

		EmployeeServices employeeService = new EmployeeServices();
		
		try {
			TransformUtil.requestTransform();
			employeeService.employeeFromXml();
			employeeService.employeeTableCreate();
			employeeService.employeesAdd();
			employeeService.employeeGetById("EMP10004");
			employeeService.employeeDelete("EMP10001");
			employeeService.employeeDisplay();
		
				
				
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}	
		
	}

}