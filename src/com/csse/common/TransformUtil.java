package com.csse.common;


import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;


public class TransformUtil extends ConfigUtil {

//Array list for store employees  
private static final ArrayList<Map<String, String>> _employeeList= new ArrayList<Map<String, String>>();

//Map for store employee details
	private static Map<String, String> _employeeData = null;
	
	//Constant for file paths
	private static String _EmployeeRequestPathString  = "src/com/csse/config/EmployeeRequest.xml";
	private static String _EmployeeModifiedPathString = "src/com/csse/config/Employee-modified.xsl";
	private static String _EmployeeResponsePathString = "src/com/csse/config/EmployeeResponse.xml";
	

	public static void  requestTransform() throws Exception {

		Source _requestSource = new StreamSource(new File(_EmployeeRequestPathString));
		Source _modifiedSource = new StreamSource(new File(_EmployeeModifiedPathString));
		Result _responseResult = new StreamResult(new File(_EmployeeResponsePathString));
		
		TransformerFactory.newInstance().newTransformer(_modifiedSource).transform(_requestSource, _responseResult);
	}

	public static ArrayList<Map<String, String>> XMLXPATHS() throws RuntimeException,ParserConfigurationException,NullPointerException,XPathExpressionException,IllegalArgumentException,Exception {

		Document _doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse("src/com/csse/config/EmployeeResponse.xml");
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) xPath.compile("count(//Employees/Employee)").evaluate(_doc, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			_employeeData = new HashMap<String, String>();
			_employeeData.put("XpathEmployeeIDKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put("XpathEmployeeNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put("XpathEmployeeAddressKey",
					(String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(_doc,
							XPathConstants.STRING));
			_employeeData.put("XpathFacultyNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put("XpathDepartmentKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put("XpathDesignationKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(_doc, XPathConstants.STRING));
			_employeeList.add(_employeeData);
		}
		return _employeeList;
	}
}
