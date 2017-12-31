package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.RestResponse;

public class TestDeleteMethod {
	
	/**
	 * Step 1 : I will Post the data and validate status code as 200 OK
	 * Step 2 : Call delete end point to delete the above posted data, validate for 200 OK
	 * Step 3 : Call the get end point, it should return 404 not found
	 * Step 4 : Again call the delete endpoint with same id, expected will be 404 not found
	 * */
	
	
	@Test
	public void testDelete() {
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
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestApiHelper.performDeleteRequest("http://localhost:8080/laptop-bag/webapi/api/delete/" + id, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
		
	}

}
