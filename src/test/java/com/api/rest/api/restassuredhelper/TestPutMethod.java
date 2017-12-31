package com.api.rest.api.restassuredhelper;


import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.helper.Features;
import com.api.rest.api.helper.LaptopBag;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class TestPutMethod extends BaseClass {
	
	@Test
	public void testPut() {
		
		/**
		 * Given Accept the content in JSON format
		 * With Content Type as JSON
		 * And Body
		 * When I perform the PUT request with id
		 * Then HTTP Status code 200 OK should be returned
		 * And Details should get updated
		 * */
		
		String id = (int)(1000*(Math.random())) + "";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Microsoft Surface");
		Features fet = new Features();
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
		bag.setFeatures(fet);
		
		
		given()
		.log()
		.everything()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add");
		
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive","This is a PUT method")));
		bag.setFeatures(fet);
		
		JsonPath bdy = given()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(bag)
		.when()
		.put("/update")
		.thenReturn()
		.jsonPath();
		
		Assert.assertEquals(bdy.getString("BrandName"), "Microsoft");
	}
	
	@Test
	public void testDelete() {
		
		String id = (int)(1000*(Math.random())) + "";
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Microsoft");
		bag.setId(id);
		bag.setLaptopName("Microsoft Surface");
		Features fet = new Features();
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
		bag.setFeatures(fet);
		
		
		given()
		.log()
		.everything()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(bag)
		.post("/add");
		
		expect()
		.statusCode(HttpStatus.SC_OK)
		.when()
		.delete("/delete/" + id);
		
		expect()
		.statusCode(HttpStatus.SC_NOT_FOUND)
		.when()
		.delete("/delete/1");
		
	}
	
}
