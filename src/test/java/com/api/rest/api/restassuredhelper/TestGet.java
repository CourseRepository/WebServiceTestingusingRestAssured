package com.api.rest.api.restassuredhelper;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class TestGet extends BaseClass {

	@Test
	public void testGet() throws URISyntaxException {
		/*
		 * Given Accept the response in JSON/XML format When I perform the GET
		 * request *
		 */

		// URI uri = new
		// URI("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
		Response response = given()
				.accept(ContentType.XML)
				.when()
				.get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"));
		// Response response = when().get(new
		// URI("http://localhost:8080/laptop-bag/webapi/api/all"));
		System.out.println(response.asString());
	}

	@Test
	public void testStatusCode() throws URISyntaxException {

		/*
		 * Given Accept the response in JSON format When I perform the GET
		 * request Then Status code 200 OK should be returned *
		 */

		/*
		 * int code = given() .accept(ContentType.JSON) .when() .get(new
		 * URI("http://localhost:8080/laptop-bag/webapi/api/all")) .thenReturn()
		 * .statusCode(); System.out.println(code);
		 * Assert.assertEquals(HttpStatus.SC_BAD_GATEWAY, code);
		 */

		given().accept(ContentType.JSON)
				.when()
				.get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"))
				.then().assertThat().statusCode(HttpStatus.SC_OK);

		// capture the status code / any other info : thenReturns()
		// validation of response then()

	}

	@Test
	public void testGetWithId() throws URISyntaxException {
		/**
		 * Given Accept the content in JSON format When I perform the GET
		 * request with 203 Then Status code 200 OK should be returned
		 * */

		String body = given().accept(ContentType.JSON).when()
				.get(new URI("/find/203")).thenReturn().body().asString();
		System.out.println(body);

	}

	@Test
	public void testGetWithNonExistId() throws URISyntaxException {
		/**
		 * Given Accept the content in JSON format When I perform the GET
		 * request with 121 Then Status code 404 Not Found should be returned
		 * */

		System.out.println(baseURI + port + basePath);
		given().accept(ContentType.JSON).when().get(new URI("/find/121"))
				.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void testGetWithIdWithHeader() throws URISyntaxException {
		/**
		 * Given Accept the content in JSON format When I perform the GET
		 * request with 203 Then Status code 200 OK should be returned
		 * */

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");

		String body = given().headers(headers).when().get(new URI("/find/203"))
				.thenReturn().body().asString();
		System.out.println(body);

	}

	@Test
	public void testContent() {

		/**
		 * Given Accept the content in JSON format When I perform the GET method
		 * with id as 349 Then the response should have BrandName as Dell
		 * **/

		given().accept(ContentType.JSON).when().get("/find/201").then()
				.body("Features.Feature", hasSize(2));

		/**
		 * hasItem hasItems hasSize
		 * */
	}

	@Test
	public void testXmlContent() {
		/**
		 * Given Accept the content in XML format 
		 * When I perform the GET method with id as 149 
		 * Then The response should have BrandName as Dell 
		 * And The features should have 8GB RAM
		 * **/

		given().accept(ContentType.XML)
				.when()
				.get("/find/149")
				.then()
				.assertThat()
				.body("Laptop.BrandName", equalTo("Dell"), "Laptop.Id",
						equalTo("149"), "Laptop.LaptopName",
						equalTo("Latitude"))
				.and()
				.assertThat()
				.body("Laptop.Features.Feature",
						hasItems("8GB RAM", "1TB Hard Drive"));
	}
	
	@Test
	public void testXmlContentXmlPath() {
		
		/**
		 * Given Accept the content in XML format 
		 * When I perform the GET method with id as 201 
		 * Then The response should have BrandName as Dell 
		 * And The features should have 8GB RAM
		 * **/
		
		String s = given()
		.accept(ContentType.XML)
		.when()
		.get("/find/201")
		.thenReturn()
		.asString();
		
		XmlPath xml = new XmlPath(s);
		/*System.out.println(xml.getInt("Laptop.Id"));
		System.out.println(xml.getString("Laptop.BrandName"));
		System.out.println(xml.getString("Laptop.LaptopName"));
		List<String> list =  xml.getList("Laptop.Features.Feature");
		System.out.println(list.size());*/
		
		Assert.assertEquals(201, xml.getInt("Laptop.Id"));
		Assert.assertEquals("Dell", xml.getString("Laptop.BrandName"));
		Assert.assertEquals("Latitude", xml.getString("Laptop.LaptopName"));
		Assert.assertTrue(xml.getList("Laptop.Features.Feature").contains("1TB Hard Drive"));
		
	}
	
	@Test
	public void testContentJsonPath() {
		
		/**
		 * Given Accept the content in Json format 
		 * When I perform the GET method with id as 933 
		 * Then The response should have BrandName as Dell 
		 * And The features should have 8GB RAM
		 * **/
		
		String s = given()
		.accept(ContentType.JSON)
		.when()
		.get("/find/933")
		.thenReturn()
		.asString();
		
		JsonPath json = new JsonPath(s);
		Assert.assertEquals(933, json.getInt("Id"));
		Assert.assertEquals("Dell", json.getString("BrandName"));
		Assert.assertEquals("Latitude", json.getString("LaptopName"));
		Assert.assertTrue(json.getList("Features.Feature").contains("1TB Hard Drive"));
		
		/*XmlPath xml = new XmlPath(s);
		System.out.println(xml.getInt("Laptop.Id"));
		System.out.println(xml.getString("Laptop.BrandName"));
		System.out.println(xml.getString("Laptop.LaptopName"));
		List<String> list =  xml.getList("Laptop.Features.Feature");
		System.out.println(list.size());
		
		Assert.assertEquals(75, xml.getInt("Laptop.Id"));
		Assert.assertEquals("Dell", xml.getString("Laptop.BrandName"));
		Assert.assertEquals("Latitude S Series", xml.getString("Laptop.LaptopName"));
		Assert.assertTrue(xml.getList("Laptop.Features.Feature").contains("1TB Hard Drive"));*/
		
	}
	
}
