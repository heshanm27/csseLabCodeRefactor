package com.csse.main;


import com.csse.common.TransformUtil;
import com.csse.service.EmployeeServices;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeServices employeeService = new EmployeeServices();
		try {
			TransformUtil.requestTransform();
			employeeService.employeeFromXml();;
//			employeeService.employeeTableCreate();
//			employeeService.employeesAdd();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
//			employeeService.employeeDisplay();
		} catch (Exception e) {
			System.out.println("main error");
		}	
		
	}

}