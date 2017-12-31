package com.api.twitter;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Ignore;
import org.junit.Test;

import com.api.rest.api.model.TwitterModel;

@Ignore
public class TestGetTweet {

	private final String CONSUMER_KEY = "0aWgpyaiNWqnteNKZtr2Tpin8";
	private final String CONSUMER_SECRET = "AkN1ddNHGJK28CSHM90ki9jZnx6EsUee2b1WZ5cKszgFN2q8cA";
	
	private final String ACCESS_KEY = "917811410138152960-atFLvog2ReH8sSVR4kfAcUKbFjnr6FK";
	private final String ACCESS_SECRET = "yeupPcDsXUmleE0pyCRoMT9XlhfTFmnJ2RjiBx5pYSF1U";
	
	/*
	 * https://api.twitter.com/1.1/statuses/update.json?status=Maybe%20he%27ll%20finally%20find%20his%20keys.%20%23peterfalk
	 * */
	
	@Test
	public void postStatusUpdate() throws URISyntaxException {
		URI postUri = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com/")
				.setPath("1.1/statuses/update.json")
				.addParameter("status", "This-is-from-Rest-Assured")
				.build();
				
		given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_KEY, ACCESS_SECRET)
		.when()
		.post(postUri)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void getFollowers() throws URISyntaxException{
		
		URI getUri = new URIBuilder()
		.setScheme("https")
		.setHost("api.twitter.com/")
		.setPath("1.1/followers/list.json")
		.setParameter("screen_name", "Schwarzenegger")
		.setParameter("count", "2")
		.setParameter("skip_status", "true")
		.setParameter("include_user_entities", "false")
		.build();
	
		String respose = given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_KEY, ACCESS_SECRET)
		.when()
		.get(getUri)
		.thenReturn()
		.asString();
	
		System.out.println(respose);
	
	}
	
	@Test
	public void deleteTweet() throws URISyntaxException {
		URI postUri = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com/")
				.setPath("1.1/statuses/update.json")
				.addParameter("status", "This-is-from-Rest-Assured" + Math.random())
				.build();
				
		TwitterModel postResponse = given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_KEY, ACCESS_SECRET)
		.when()
		.post(postUri)
		.thenReturn()
		.as(TwitterModel.class);
		
		System.out.println(postResponse.id_Str);
		
		URI deleteUri = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com/")
				.setPath("1.1/statuses/destroy/" + postResponse.id_Str + ".json")
				.build();
		
		String delete = given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_KEY, ACCESS_SECRET)
		.when()
		.post(deleteUri)
		.thenReturn()
		.asString();
		
		System.out.println(delete);
	}
}
