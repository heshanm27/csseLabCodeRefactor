package com.csse.service;

import java.util.ArrayList;

import com.csse.modals.Employee;

public abstract class AbstractServices {
	
	/**
	 * create from xml
	 * 
	 * @return void
	 * 
	 *
	 */
	public abstract void employeeFromXml();
	public abstract void employeeTableCreate();
	public abstract void employeesAdd();
	public abstract void employeeGetById(String empId);
	public abstract void employeeDelete(String empId);
	public abstract void employeeDisplay();
	public abstract void employeeOutput(ArrayList<Employee> employeeList);
	
}
