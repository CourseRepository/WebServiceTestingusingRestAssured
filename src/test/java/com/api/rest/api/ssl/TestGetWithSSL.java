package com.api.rest.api.ssl;

import io.restassured.http.ContentType;

import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;


public class TestGetWithSSL extends BaseSSLClass  {
	
	@Test
	public void testGet() {
		String s = given()
				.relaxedHTTPSValidation()
		.accept(ContentType.XML)
		.when()
		.get("/all")
		.thenReturn()
		.asString();
		System.out.println(s);
		
		/**
		 * 1. To bypass the certificate check
		 * 2. Is to supply valid certificate along with request
		 * 
		 * */
		
	}
	
	@Test
	public void testGetWithCertificate() {
		
		given()
		.log()
		.all()
		.when()
		.get("/all")
		.then()
		.assertThat()
		.body("$", is(notNullValue()));
		
	}

}
