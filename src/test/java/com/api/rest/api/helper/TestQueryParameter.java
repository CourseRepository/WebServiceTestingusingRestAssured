package com.api.rest.api.helper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.api.rest.api.model.RestResponse;

public class TestQueryParameter {
	
	@Test
	public void testQuery() throws URISyntaxException {
		URI url = new URIBuilder()
		.setScheme("http") // https
		.setHost("localhost:8080/")
		.setPath("laptop-bag/webapi/api/query")
		.setParameter("id", "444")
		.setParameter("laptopName", "Latitude")
		.build();
		
		RestResponse response = RestApiHelper.performGetRequest(url, null);
		System.out.println(response.toString());
		
		
	}

}
