package com.api.rest.api.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestPostMethod {
	
	
	
	@Test
	public void testPost() {
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
		Assert.assertEquals("Latitude", body.getLaptopName());
		Assert.assertEquals(expectedFeature, body.getFeatures().getFeature());
		
	}
	
	@Test
	public void testPostWithXml() throws JsonParseException, JsonMappingException, IOException {
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
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		XmlMapper xml = new XmlMapper();
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ResponseBody body = xml.readValue(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals("Dell", body.getBrandName());
		Assert.assertEquals("Latitude", body.getLaptopName());
		System.out.println(body.getFeatureList().size());
	}
	
	@Test
	public void TestFormDataFileUpload() {
		String url = "http://localhost:9191/normal/webapi/upload";
		File upload = new File("C:\\Data\\log\\book.zip");
		RestResponse response = RestApiHelper.performPostRequest(url, upload, ContentType.MULTIPART_FORM_DATA,
				new LinkedHashMap<String, String>());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		String path = System.getProperty("java.io.tmpdir") + upload.getName();
		File verify = new File(path);
		Assert.assertTrue("File upload is not successfull", verify.exists());
	}

}
