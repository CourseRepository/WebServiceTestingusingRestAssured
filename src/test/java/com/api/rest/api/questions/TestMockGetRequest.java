package com.api.rest.api.questions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class TestMockGetRequest {
	private static final int PORT = 3030;
    private static final String HOST = "localhost";
    private static WireMockServer server = new WireMockServer(PORT);

    @BeforeClass(enabled = true)
    public static void setup()
    {
        ResponseDefinitionBuilder mockresponse = new ResponseDefinitionBuilder();
        mockresponse.withStatus(200);
        server.start();
        WireMock.configureFor(HOST, PORT);
        WireMock.stubFor(WireMock.get("/products").willReturn(mockresponse));
    }


    @AfterClass(enabled = true)
    public void stop()
    {
    	if(server != null && server.isRunning())
    		server.shutdownServer();
    }
    
    @Test
    public static void mockGetRequest()
    {
        // http://localhost:3030/products?$limit=1
        Response response =
            RestAssured.given().log().all().request(Method.GET, " http://localhost:3030/products").andReturn();
        System.out.println("Response is  " + response.asString());
        System.out.println("Response code is  " + response.statusCode());

    }

}
