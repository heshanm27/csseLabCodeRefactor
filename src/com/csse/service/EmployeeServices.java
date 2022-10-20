package com.csse.service;


import com.csse.common.CommonConstants;
import com.csse.common.DbConnectionUtill;
import com.csse.common.QueryUtil;
import com.csse.common.TransformUtil;
import com.csse.modals.Employee;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Map;

/**
 * 
 * Includes Database Data Storage functions and XML Data retrieval functions
 * 
 * XML data retrieval function : employeeFromXML 
 *
 */

public class EmployeeServices extends AbstractServices {

	private final ArrayList<Employee> employeeList= new ArrayList<Employee>();
	
	private static final Logger log = Logger.getLogger(EmployeeServices.class.getName());
	private static Connection connection ;

	private static Statement statement ;

	private PreparedStatement preparedStatement;

	public EmployeeServices() {
		connection = DbConnectionUtill.getConnection();
	}

	@Override
	
	/**
	 * Stores data collected from the XML file inside an global variable
	 * This information is accessible from any class
	 * 
	 *  @return void
	 */
	
	public void employeeFromXml() {
		
		try {
			for (Map<String, String> docMap : TransformUtil.xmlPaths()) {
				Employee employee= new Employee();
				employee.setEmployeeId(docMap.get(CommonConstants.TransformUtil.XPATH_EMPLOYEE_ID));
				employee.setFullName(docMap.get(CommonConstants.TransformUtil.XPATH_EMPLOYEE_NAME));
				employee.setAddress(docMap.get(CommonConstants.TransformUtil.XPATH_EMPLOYEE_ADDRESS));
				employee.setFacultyName(docMap.get(CommonConstants.TransformUtil.XPATH_EMPLOYEE_FACULTY));
				employee.setDepartment(docMap.get(CommonConstants.TransformUtil.XPATH_EMPLOYEE_DEPARTMENT));
				employee.setDesignation(docMap.get(CommonConstants.TransformUtil.XPATH_EMPLOYEE_DESIGNATION));
				employeeList.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (IllegalArgumentException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch (NullPointerException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch (RuntimeException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch (ParserConfigurationException e) {
			log.log(Level.SEVERE,e.getMessage());
		}catch (XPathExpressionException e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		
	}

	/**
	 * Creates Employee table inside sql database
	 * @return void
	 * @exception java.sql.SQLException
	 */
	
	@Override
	public void employeeTableCreate() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(QueryUtil.query("q2"));
			statement.executeUpdate(QueryUtil.query("q1"));
		} catch (SQLException e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		
	}


	/**
	 * Inserts a new employee to employee table in sql database
	 * @return void
	 * @exception java.sql.SQLException
	 */
	
	@Override
	public void employeesAdd() {
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.query("q3"));
			connection.setAutoCommit(false);
			
			for(Employee employee: employeeList) {
				preparedStatement.setString(1, employee.getEmployeeId());
				preparedStatement.setString(2, employee.getFullName());
				preparedStatement.setString(3, employee.getAddress());
				preparedStatement.setString(4, employee.getFacultyName());
				preparedStatement.setString(5, employee.getDepartment());
				preparedStatement.setString(6, employee.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		
		}catch (SQLException e) {
			log.log(Level.SEVERE,e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		
		
	}
	

	/**
	 * provides employee information for given employee id
	 * @return void
	 * @param String
	 * @exception java.sql.SQLException
	 */

	@Override
	public void employeeGetById(String empId) {
		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.query("q4"));
			preparedStatement.setString(1, empId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setEmployeeId(resultSet.getString(1));
				employee.setFullName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
			}
			
			
			ArrayList<Employee> empList = new ArrayList<Employee>();
			empList.add(employee);
			employeeOutput(empList);
		}catch (SQLException e) {
			log.log(Level.SEVERE,e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		
	}

	/**
	 * delete employee from sql database for given employee id
	 * @return void
	 * @param String
	 * @exception java.sql.SQLException
	 */
	
	@Override
	public void employeeDelete(String empId) {
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.query("q6"));
			preparedStatement.setString(1, empId);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			log.log(Level.SEVERE,e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		
	}

	/**
	 * read employee table from sql database
	 * @return void
	 * @param String
	 * @exception java.sql.SQLException
	 */
	
	@Override
	public void employeeDisplay() {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.query("q5"));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getString(1));
				employee.setFullName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
				empList.add(employee);
				
			}
		}catch (SQLException e) {
			log.log(Level.SEVERE,e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
		employeeOutput(empList);
		
	}

	/**
	 * displays list of employees for given employees list
	 * @return void
	 * @param ArrayList<Employee>
	 * @exception java.sql.SQLException
	 */
	
	@Override
	public void employeeOutput(ArrayList<Employee> employeeList) {
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		
		
		for(Employee employee : employeeList) {
			System.out.println(employee.getEmployeeId() + "\t" + employee.getFullName() + "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}

	
}
