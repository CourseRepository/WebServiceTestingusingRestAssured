package com.api.rest.api.restassuredhelper;

import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;

public class BaseClass {
	
	@BeforeClass
	public static void setUp() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/api";
	}
	
	public BaseClass() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/api";
	}
	
	// 1. To create the Cons
	// 2. Hooks -> BDD concept

}
