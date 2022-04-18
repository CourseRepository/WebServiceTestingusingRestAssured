package com.api.rest.api.restassuredhelper.mocks.post;

import static org.hamcrest.Matchers.equalTo;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TestPostWithMock extends MockTestBase {
	
	private static final String HOST = "localhost";
	private static final Integer PORT = 8080;
	private static WireMockServer server = null;
	
	private final static String body = "{\"BrandName\":\"Alienware\",\"Features\":{\"Feature\":[\"8th Generation Intel® Core™ i5-8300H\",\"Windows 10 Home 64-bit English\",\"NVIDIA® GeForce® GTX 1660 Ti 6GB GDDR6\",\"8GB, 2x4GB, DDR4, 2666MHz\"]},\"Id\":100,\"LaptopName\":\"Alienware M17\"}";
	
	
	@BeforeClass
	public static void setup() {
		server = new WireMockServer(PORT);
		server.start();
		WireMock.configureFor(HOST, PORT);
		
	}
	
	@Test
	public void testPostReques() throws URISyntaxException {
		setStubProvider(new PostStubProvider("/laptop-bag/webapi/api/add", new DefaultResponseBody()));
		registerStub();
		
		//
		RestAssured.given()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.body(body)
		.when()
		.post(new URI("http://" + HOST + ":" + PORT + "/laptop-bag/webapi/api/add"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.body("LaptopName", equalTo("Alienware M17"));
		
	}
	
	@AfterClass
	public static void teardown() {
		if(server != null && server.isRunning())
			server.shutdown();
		
	}

}
