package com.csse.service;

import java.util.ArrayList;

import com.csse.modals.Employee;

/**
 * 
 * Abstract class for EmployeeServices Class
 * Provides Template for EmployeServices class
 *
 */

public abstract class AbstractServices {
	
	public abstract void employeeFromXml();
	public abstract void employeeTableCreate();
	public abstract void employeesAdd();
	public abstract void employeeGetById(String empId);
	public abstract void employeeDelete(String empId);
	public abstract void employeeDisplay();
	public abstract void employeeOutput(ArrayList<Employee> employeeList);
	
}
