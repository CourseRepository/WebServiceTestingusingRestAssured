package com.api.rest.api.jwtauth;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class TestEndPointWithJwt {

	/**
	 * 1. Register the user with the end point https://jobapplicationjwt.herokuapp.com/users/sign-up
	 * 2. Authenticate and generate the token https://jobapplicationjwt.herokuapp.com/users/authenticate
	 * 3. Extract the token using Json path
	 * 4. Send the request with jwt token https://jobapplicationjwt.herokuapp.com/auth/webapi/all
	 * */
	
	private String body = "{  \"password\": \"Guns and Bikes\",  \"username\": \"John Wick\"}";
	private String token = "";
	
	@Before
	public void setup() {
		// Register the user
		given()
		.contentType(ContentType.JSON)
		.body(body)
		.when()
		.post("https://jobapplicationjwt.herokuapp.com/users/sign-up")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
		//Generate the token
		
		token = given()
		.contentType(ContentType.JSON)
		.body(body)
		.when()
		.post("https://jobapplicationjwt.herokuapp.com/users/authenticate")
		.jsonPath()
		.get("token");
		
	}
	
	@Test
	public void testGetWithJwt() {
		Map<String, String> headers = new HashMap<String, String>(){
			{
				put("Accept", "application/json");
				put("Authorization", "Bearer " + token);
			}
		};
		
		
		given()
		.headers(headers)
		.when()
		.get("https://jobapplicationjwt.herokuapp.com/auth/webapi/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void testGetWithOutJwt() {
		Map<String, String> headers = new HashMap<String, String>(){
			{
				put("Accept", "application/json");
				
			}
		};
		
		
		given()
		.headers(headers)
		.when()
		.get("https://jobapplicationjwt.herokuapp.com/auth/webapi/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
}
