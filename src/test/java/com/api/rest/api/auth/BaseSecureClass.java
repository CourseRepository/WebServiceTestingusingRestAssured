package com.api.rest.api.auth;

import static io.restassured.RestAssured.*;

import org.junit.BeforeClass;

public class BaseSecureClass {
	
	@BeforeClass
	public static void setUp() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/api";
		authentication = preemptive().basic("admin", "welcome");
		
	}

}
