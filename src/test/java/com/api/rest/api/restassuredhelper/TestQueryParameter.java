package com.api.rest.api.restassuredhelper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class TestQueryParameter extends BaseClass {

	@Test
	public void testQueryPra() {
		
		/**
		 * Given Accept the content in JSON format
		 * And ID as 75
		 * And Laptop Name as Latitude S Series
		 * When I perform the GET Request
		 * Then Status code 200 OK should be returned
		 * And The response content should have id as 75
		 * And Feature list should contain 1024 GB of SSD
		 * 
		 * **/
		
		 given()
		.accept(ContentType.JSON)
		.param("id", "75")
		.param("laptopName", "Latitude S Series")
		.when()
		.get("/query")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.assertThat()
		.body("Features.Feature", hasItem("1024 GB of SSD"));
		
	}
	
}
