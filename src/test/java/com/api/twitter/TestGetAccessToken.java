package com.api.twitter;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Ignore;
import org.junit.Test;

import com.api.rest.api.model.TwitterTokenModel;

@Ignore
public class TestGetAccessToken {
	private final String CONSUMER_KEY = "0aWgpyaiNWqnteNKZtr2Tpin8";
	private final String CONSUMER_SECRET = "AkN1ddNHGJK28CSHM90ki9jZnx6EsUee2b1WZ5cKszgFN2q8cA";
	private final String HEADER ="oauth_consumer_key=0aWgpyaiNWqnteNKZtr2Tpin8,"
			+"oauth_nonce=K7ny27JTpKVsTgdyLdDfmQQWVLERj2zAK5BslRsqyw,"
			+"oauth_signature=feKWJnOmdlf7fc0WeM4YfPeTBL8%3D"
			+ "oauth_signature_method=HMAC-SHA1,"
			+ "oauth_timestamp=1507832673,"
			+ "oauth_version=1.0";
	
	@Test
	public void getAccessToken() throws URISyntaxException {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization",HEADER);
		
		URI getUri = new URIBuilder()
		.setScheme("https")
		.setHost("api.twitter.com/")
		.setPath("oauth/request_token")
		.build();
		
		String response = given()
		.when()
		.log()
		.headers()
		.headers(headers)
		.post(getUri)
		.thenReturn()
		.asString();
		
		System.out.println(response);
		
		Map<String,String> decodedResponse = getDecodedResponse(response);
		
		URI postUri = new URIBuilder()
				.setScheme("https")
				.setHost("api.twitter.com/")
				.setPath("1.1/statuses/update.json")
				.addParameter("status", "This-is-from-Rest-Assured")
				.build();
				
		String str = given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, decodedResponse.get("oauth_token"), decodedResponse.get("oauth_token_secret"))
		.when()
		.post(postUri)
		.thenReturn()
		.asString();
		
		System.out.println(str);
		
	}

	private Map<String, String> getDecodedResponse(String response) {
		HashMap<String, String> data = new HashMap<>();
		String[] token = response.split("&");
		
		for (String args : token) {
			String[] pair = args.split("=");
			data.put(pair[0], pair[1]);
		}
		
		return data;
		
	}

}
