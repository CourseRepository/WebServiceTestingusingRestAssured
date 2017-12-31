package com.api.rest.api.restassuredhelper.hooks;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class GeneralHooks {
	
	@Before
	public void Setup(Scenario scenarioInfo) {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/api";
	}
	
	@After
	public void TearDown(Scenario scenarioInfo) {
		//Cleanup - Resources
	}

}
