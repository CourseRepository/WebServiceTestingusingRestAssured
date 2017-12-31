package com.api.rest.api.auth;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.api.rest.api.helper.Features;
import com.api.rest.api.helper.LaptopBag;

public class TestGetWithSecure extends BaseSecureClass {
	
	@Test
	public void testGetWithoutAuth() {
		expect()
		.statusCode(HttpStatus.SC_OK)
		.when()
		.get("/all");
	}
	
	@Test
	public void testGetWithAuth() {
		
		/*
		 * 1. Using Header
		 * 2. Method form RA
		 * */
		
		given()
		//.log()
		//.all()
		.header("Authorization", "Basic YWRtaW46d2VsY29tZQ==")
		.when()
		.get("/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
	}
	
	@Test
	public void testBasicAuth() {
		
		/**
		 * Preemptive Basic Authentication :- It will always send the u/p along with req
		 * 
		 * Challenged Basic Authentication :- It will send u/p only when server explictly ask for it
		 * 
		 * */
		
		given()
		//.auth()
		//.preemptive()
		//.basic("admin", "welcome")
		.log()
		.all()
		.when()
		.get("/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	
	@Test
	public void testPostwithObjectMapping() {
		
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
		.body()
		.accept(ContentType.XML)
		.with()
		//.auth()
		//.preemptive()
		//.basic("admin", "welcome")
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Laptop.Id", equalTo(id));
		
		
	}
}
