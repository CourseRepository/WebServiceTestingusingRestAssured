package com.api.twitter;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestGetStatusList {
	
	private final String CONSUMER_KEY = "k0GDexaSGsJzIlQxEwGQAY7su";
	private final String CONSUMER_SCERET = "XwfYlclCulFWEvCqczumUTUJYoqGaxKFp6sx7zYQLV8y4UFILJ";
	private final String ACCESS_TOKEN = "917811410138152960-itj0vVAYwPNqtzIfjus9xEGdMZFofGh";
	private final String ACCESS_SCERET = "xEFi7ADsxdmHi7tXhT8Bqxl3wOisJZJmFyEubpUcmsgjp";
	
	private final String UPLOAD_URL = "https://api.twitter.com/1.1/statuses/update.json";
	
	

	
	@Test
	public void testGetStatus() throws URISyntaxException{
		
		URI builder  = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com")
				.setPath("/1.1/statuses/update.json")
				.addParameter("status", "This Post is from Rest Api")
				.build();
		
		String returnValue =given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SCERET, ACCESS_TOKEN, ACCESS_SCERET)
		.when()
		//.post("https://api.twitter.com/1.1/statuses/update.json?status=Maybe%20he%27ll%20finally%20find%20his%20keys.%20%23peterfalk")
		.post(builder)
		.thenReturn()
		.asString();
		
		System.out.println(returnValue);
	}
}
