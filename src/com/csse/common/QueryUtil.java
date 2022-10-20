package com.csse.common;

import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.File;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;


/**
 * 
 * This class contains the XML Query
 *
 */

public class QueryUtil extends ConfigUtil {
	
	public static final String _BYTAG_NAME_STRING = "query";
	public static final String _ATTRIBUTETAG_STRING = "id";
	
	
	public static String query(String id) throws TransformerFactoryConfigurationError,Exception {
		
		 NodeList _nodeList;
		 Element _element= null;
		
			
		_nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(CommonConstants.QueryUtil.EMPLOYEE_QUERY_PATH_STRING))
				.getElementsByTagName(_BYTAG_NAME_STRING);
		
		for (int x = 0; x < _nodeList.getLength(); x++) {
			
			_element = (Element) _nodeList.item(x);
			
			if (_element.getAttribute(_ATTRIBUTETAG_STRING).equals(id))
				break;
		}
		return _element.getTextContent().trim();
	}
}
