package com.api.rest.api.restassuredhelper.mocks.post;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class PostStubProvider implements AbstractStubProvider {
	private AbstractResponseBodyProvider bodyProvider;
	private String contextPath;
	
	
	public PostStubProvider(String contextPath,AbstractResponseBodyProvider bodyProvider) {
		this.contextPath = contextPath;
		this.bodyProvider = bodyProvider;
	}

	@Override
	public MappingBuilder getStub() {
		
		/*
		 * When the request POST is receive on the given contextPath
		 * with header Accept == application/json & Content-Type == application/json
		 * with request body having Id and BrandName json property
		 * Then
		 * respond with status code 200 OK and with the response body
		 * 
		 * */
		
		return WireMock.post(this.contextPath)
		.withHeader(HttpHeaders.ACCEPT, WireMock.containing(ContentType.APPLICATION_JSON.getMimeType()))
		.withHeader(HttpHeaders.CONTENT_TYPE, WireMock.containing(ContentType.APPLICATION_JSON.getMimeType()))
		.withRequestBody(WireMock.matchingJsonPath("$.Id"))
		.withRequestBody(WireMock.matchingJsonPath("$.BrandName"))
		.willReturn(getResponseBody());
		
		
	}
	
	private ResponseDefinitionBuilder getResponseBody() {
		return new ResponseDefinitionBuilder()
				.withStatus(HttpStatus.SC_OK)
				.withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
				.withBody(bodyProvider.getResponseBody().toString());
	}
	

}
