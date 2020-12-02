package com.api.rest.api.questions.schema.validation;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class TestJsonSchemaValidation {

	@Test
	public void testJsonSchema() throws URISyntaxException {
		String id = (int) (1000 * (Math.random())) + "";

		String jsonBody = "{" + "\"BrandName\": \"Dell\"," + "\"Features\": {" + "\"Feature\": [\"8GB RAM\","
				+ "\"1TB Hard Drive\"]" + "}," + "\"Id\": " + id + "," + "\"LaptopName\": \"Latitude\"" + "}";

		/*POST Request to create a new Entry**/
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(jsonBody)
		.when()
		.post("http://localhost:8080/laptop-bag/webapi/api/add")
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK);
		
		/* GET request for the schema validation **/
		given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"))
		.then()
		.assertThat()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
	}

}
