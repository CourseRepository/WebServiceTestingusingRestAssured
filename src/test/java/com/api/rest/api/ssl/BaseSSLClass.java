package com.api.rest.api.ssl;

import static io.restassured.RestAssured.*;

import org.junit.BeforeClass;

public class BaseSSLClass {
	
	@BeforeClass
	public static void setUp() {
		baseURI = "https://localhost";
		port = 8443;
		basePath = "/laptop-bag/webapi/sslres";
		authentication = certificate("C:\\Users\\rahul.rathore\\Desktop\\Rest\\Video\\Resource\\mykey.keystore", "password");
	}

}
