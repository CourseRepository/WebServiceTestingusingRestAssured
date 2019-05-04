package com.api.rest.api.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class TestPostMethodWithParametrized {
	
	@Test
	@FileParameters("src/test/resources/testdata.csv")
	public void testPostWithParameter(String brandName){
		String id = (int)(1000*(Math.random())) + "";
		List<String> expectedFeature = new ArrayList<>(Arrays.asList("8GB RAM", "1TB Hard Drive"));
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
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals(id, body.getId());
		Assert.assertEquals(brandName, body.getLaptopName());
		Assert.assertEquals(expectedFeature, body.getFeatures().getFeature());
	}

}
