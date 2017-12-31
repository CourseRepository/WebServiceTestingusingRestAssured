package com.api.rest.api.helper;

import java.io.IOException;
import java.util.LinkedHashMap;
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

public class TestPutMethod {
	
	/***
	 * Step 1 : Call the Post method, validation will be 200 OK
	 * Step 2 : Call the Put method which will update the content , 200 OK
	 * Step 3 : Call the Get endpoint to validate the output , content validation
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */

	
	@Test
	public void testPut() throws JsonParseException, JsonMappingException, IOException {
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
		
		String xmlBody = "<Laptop>" + "<BrandName>Dell</BrandName>"
				+ "<Features>" + "<Feature>8GB RAM</Feature>"
				+ "<Feature>1TB Hard Drive</Feature>"
				+ "<Feature>15.5 inch LCD</Feature>"
				+ "<Feature>1024 GB of SSD</Feature>"
				+ "<Feature>4GB of Graphic card</Feature>"
				+ "<Feature>This is Put</Feature>" + "</Features>"
				+ "<Id>" + id + "</Id>" + "<LaptopName>Latitude S Series</LaptopName>"
				+ "</Laptop>";
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		headers.clear();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/xml");
		response = RestApiHelper.performPutResquest("http://localhost:8080/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		XmlMapper mapper = new XmlMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ResponseBody body = mapper.readValue(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals("Latitude S Series", body.getLaptopName());
		
	}
	
	@Test
	public void testPutNotFound() throws JsonParseException, JsonMappingException, IOException {
		
		String xmlBody = "<Laptop>" + "<BrandName>Dell</BrandName>"
				+ "<Features>" + "<Feature>8GB RAM</Feature>"
				+ "<Feature>1TB Hard Drive</Feature>"
				+ "<Feature>15.5 inch LCD</Feature>"
				+ "<Feature>1024 GB of SSD</Feature>"
				+ "<Feature>4GB of Graphic card</Feature>"
				+ "<Feature>This is Put</Feature>" + "</Features>"
				+ "<Id>1111</Id>" + "<LaptopName>Latitude S Series</LaptopName>"
				+ "</Laptop>";
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/xml");
		RestResponse response = RestApiHelper.performPutResquest("http://localhost:8080/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
		
	}
}
