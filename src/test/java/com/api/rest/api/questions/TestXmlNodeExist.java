package com.api.rest.api.questions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.io.StringReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import io.restassured.http.ContentType;;

public class TestXmlNodeExist {

	/**
	 * How do I check if a particular node is present in the XML using rest assured?
	 * @throws DocumentException 
	 * */
	
	@Test
	public void testXmlNodePresent() throws DocumentException  {
		String xml = given()
		.accept(ContentType.XML)
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/api/all")
		.andReturn()
		.asString();
		
		// Using Dom4j parser for xml
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new StringReader(xml));
		// Using XPath to search a node in the XML Doc
		List<Node> node = doc.selectNodes("/laptopDetailss/Laptop/BrandName");
		assertTrue(node.size() >= 1,"Node is missing in response");
		
		
	}
}
