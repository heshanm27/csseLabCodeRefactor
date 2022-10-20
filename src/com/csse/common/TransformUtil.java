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
	
private static final ArrayList<Map<String, String>> _employeeList= new ArrayList<Map<String, String>>();

	private static Map<String, String> _employeeData = null;
	 
	/**
	 * 
	 * This method converts request XML file into response XML file
	 * @return void
	 */
	
	public static void  requestTransform() throws Exception {

		Source _requestSource = new StreamSource(new File(CommonConstants.TransformUtil.EMPLOYEE_REQUEST_PATH_STRING));
		Source _modifiedSource = new StreamSource(new File(CommonConstants.TransformUtil.EMPLOYEE_MODIFIED_PATH_STRING));
		Result _responseResult = new StreamResult(new File(CommonConstants.TransformUtil.EMPLOYEE_RESPONSE_PATH_STRING));
		
		TransformerFactory.newInstance().newTransformer(_modifiedSource).transform(_requestSource, _responseResult);
	}


	/**
	 * 
	 * This method converts XML inputs into an ArrayList of Employee Objects 
	 * @return ArrayList<Map<String, String>>
	 */
	
	
	public static ArrayList<Map<String, String>> xmlPaths() throws RuntimeException,ParserConfigurationException,NullPointerException,XPathExpressionException,IllegalArgumentException,Exception {

		Document _doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(CommonConstants.TransformUtil.EMPLOYEE_RESPONSE_PATH_STRING);
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) xPath.compile(CommonConstants.TransformUtil.COUNTER_PATH).evaluate(_doc, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			
			_employeeData = new HashMap<String, String>();
			
			_employeeData.put(CommonConstants.TransformUtil.XPATH_EMPLOYEE_ID, (String) xPath.compile(CommonConstants.TransformUtil.getCompliePath( i, CommonConstants.TransformUtil.SUB_PATH_EMP_ID))
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put(CommonConstants.TransformUtil.XPATH_EMPLOYEE_NAME, (String) xPath.compile(CommonConstants.TransformUtil.getCompliePath( i, CommonConstants.TransformUtil.SUB_PATH_EMP_FULL_NAME) )
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put(CommonConstants.TransformUtil.XPATH_EMPLOYEE_ADDRESS,
					(String) xPath.compile(CommonConstants.TransformUtil.getCompliePath( i, CommonConstants.TransformUtil.SUB_PATH_EMP_ADDRESS)).evaluate(_doc,
							XPathConstants.STRING));
			_employeeData.put(CommonConstants.TransformUtil.XPATH_EMPLOYEE_FACULTY, (String) xPath.compile(CommonConstants.TransformUtil.getCompliePath( i, CommonConstants.TransformUtil.SUB_PATH_EMP_FACULTY_NAME))
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put(CommonConstants.TransformUtil.XPATH_EMPLOYEE_DEPARTMENT, (String) xPath.compile(CommonConstants.TransformUtil.getCompliePath( i, CommonConstants.TransformUtil.SUB_PATH_EMP_DEPARTMENT) )
					.evaluate(_doc, XPathConstants.STRING));
			_employeeData.put(CommonConstants.TransformUtil.XPATH_EMPLOYEE_DESIGNATION, (String) xPath.compile(CommonConstants.TransformUtil.getCompliePath( i, CommonConstants.TransformUtil.SUB_PATH_EMP_DESIGNATION) )
					.evaluate(_doc, XPathConstants.STRING));
			_employeeList.add(_employeeData);
		}
		return _employeeList;
	}
}
