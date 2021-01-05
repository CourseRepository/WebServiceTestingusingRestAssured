package com.api.rest.api.questions;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.helper.RestApiHelper;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGetMethodIllegalStateException {

	/**
	 * Trying the same approach given in tutorial. getting
	 * "com.google.gson.JsonSyntaxException: java.lang.IllegalStateException" at
	 * this line: ResponseBody body = gson.fromJson(response.getResponseBody().toString(), ResponseBody.class);
	 * Please check following logs: com.google.gson.JsonSyntaxException:
	 * java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at
	 * line 1 column 10 path $.data at
	 * com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:226)
	 */
	
	@Test
	public void testGetFindWithId() {
		String url = "https://reqres.in/api/users/2";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");

		RestResponse response = RestApiHelper.performGetRequest(url, headers);
		System.out.println(response.toString());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		/*
		 * 1. Use rhe GSON builder class to get the instance of GSON class ' 2. Use the
		 * GSON object to deseralize the json
		 */

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		JSONResponseBody body = gson.fromJson(response.getResponseBody().toString(), JSONResponseBody.class);
		System.out.println(body);

	}
}

class Data {
	public int id;
	public String email;
	public String first_name;
	public String last_name;
	public String avatar;
}

class Support {
	public String url;
	public String text;
}

class JSONResponseBody {
	public Data data;
	public Support support;
}
