package com.api.rest.api.oauth;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestAccessToken {
	
	private static String OAUTH_TOKEN = "";
	
	@BeforeClass
	public static void setup() {
		baseURI = "http://coop.apps.knpuniversity.com";
		
		JsonPath token = given()
				.formParam("client_id", "TestOAuth")
				.formParam("client_secret", "<your_client_secret>")
				.formParam("grant_type", "client_credentials")
		.when()
		.post("/token")
		.andReturn()
		.jsonPath();
		
		OAUTH_TOKEN = token.getString("access_token");
	}
	
	@Test
	public void testPost() {
				given()
				.auth()
				.oauth2(OAUTH_TOKEN)
		.when()
		.post("/api/1332/chickens-feed")
		.then()
		.assertThat()
		.body("action", equalToIgnoringCase("chickens-feed"));
		
	}
	
	@Test
	public void testPostCollect() {
				given()
				.auth()
				.oauth2(OAUTH_TOKEN)
		.when()
		.post("/api/1332/eggs-collect")
		.then()
		.assertThat()
		.body("action", equalToIgnoringCase("eggs-collect"));
		
		
	}
	
	@Test
	public void testPostCount() {
				given()
				.auth()
				.oauth2(OAUTH_TOKEN)
		.when()
		.post("/api/1332/eggs-count")
		.then()
		.assertThat()
		.body("action", equalToIgnoringCase("eggs-count"));
		
	}
	
	@Test
	public void testAccesPriv() {
		
		given()
		.auth()
		.oauth2(OAUTH_TOKEN)
		.when()
		.get("/api/me")
		.then()
		.assertThat()
		.body("error", equalToIgnoringCase("insufficient_scope"));
		
	}

}
