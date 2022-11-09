package com.api.rest.api.questions.deser.xmlresponse;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.api.rest.api.model.ResponseBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestDeSerializeXml {
	
	@Test
	public void testGet() throws URISyntaxException, JsonParseException, JsonMappingException, IOException {
		
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
		.post("http://localhost:8081/laptop-bag/webapi/api/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
		Response response = given()
				.accept(ContentType.XML)
				.when()
				.get(new URI("http://localhost:8081/laptop-bag/webapi/api/find/" + id));
		String responseInXml = response.asString();
		XmlMapper xml = new XmlMapper();
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ResponseBody responseObject = xml.readValue(responseInXml, ResponseBody.class);
		assertNotNull(responseObject);
	}

}
