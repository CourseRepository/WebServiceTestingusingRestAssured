package com.api.rest.api.restassuredhelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;

import io.restassured.http.ContentType;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.helper.Features;
import com.api.rest.api.helper.LaptopBag;

public class TestPostMethod extends BaseClass {
	
	
	@Test
	public void testPost() {
		
		/****
		 * Given Accept the content in XML Format
		 * With Content Type as JSON
		 * And Body
		 * When I perform the Post request
		 * Then Status code 200 OK should be returned 
		 * And The response should contain the ID
		 */
		
		String id = (int)(1000*(Math.random())) + "";
		
		String jsonBody = "{" +
				"\"BrandName\": \"Dell\"," +
				"\"Features\": {" +
					"\"Feature\": [\"8GB RAM\"," +
					"\"1TB Hard Drive\"]"+
				"}," +
				"\"Id\": " + id + "," +
				"\"LaptopName\": \"Latitude\"" +
			"}";
		
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(jsonBody)
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.body("Laptop.Id", equalTo(id));
		
	}
	
	@Test
	public void testPostWithoutBody() {
		
		/****
		 * Given Accept the content in XML Format
		 * With Content Type as JSON
		 * When I perform the Post request
		 * Then Status code 400 should be returned 
		 */
		
		
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
		
	}	
	
	@Test
	public void testPostwithObjectMapping() {
		
		/**
		 * 1. Create the mapping class
		 * 2. Create the object of mapping class
		 * 3. Initialize the field value present in mapping class
		 * 4. Send the object along with post request
		 * **/
		String id = (int)(1000*(Math.random())) + "";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Microsoft Surface");
		Features fet = new Features();
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
		bag.setFeatures(fet);
		
		
		/****
		 * Given Accept the content in JSON Format
		 * With Content Type as XML
		 * And Body
		 * When I perform the Post request
		 * Then Status code 200 OK should be returned 
		 * And The response should contain the ID
		 */
		
		given()
		.log()
		.body()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Laptop.Id", equalTo(id));
		
		
	}
	
	@Test
	public void testPostwithDeserialization() {
		
		/**
		 * 1. Create the mapping class
		 * 2. Create the object of mapping class
		 * 3. Initialize the field value present in mapping class
		 * 4. Send the object along with post request
		 * **/
		String id = (int)(1000*(Math.random())) + "";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Microsoft Surface");
		Features fet = new Features();
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
		bag.setFeatures(fet);
		
		// Object to body (request body) :- OM Serialization
		// body (response body) to object :- Deserialization
		
		
		/****
		 * Given Accept the content in JSON Format
		 * With Content Type as XML
		 * And Body
		 * When I perform the Post request
		 * Then Status code 200 OK should be returned 
		 * And The response should contain the ID
		 */
		
		
		LaptopBag responseBag = given()
		.log()
		.everything()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add")
		.thenReturn()
		.as(LaptopBag.class);
		
		Assert.assertEquals("Microsoft", responseBag.getBrandName());
		Assert.assertEquals(id, responseBag.getId());
		Assert.assertEquals("Microsoft Surface", responseBag.getLaptopName());
		Assert.assertTrue(responseBag.getFeatures().getFeature().contains("1TB Hard Drive1"));
		
		
	}
	
}
