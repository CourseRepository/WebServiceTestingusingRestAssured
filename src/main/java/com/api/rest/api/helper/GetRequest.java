package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class GetRequest {

	/**
	 * @param args
	 * Step 1:- Create the HTTP Get/ HTTP Post / HTTP Delete / HTTP Put method
	 * Step 2:- Create the HTTP Client --> HttpAsyncClientBuilder Class
	 * Step 3:- Execute the HTTP method using the client
	 * Step 4:- Catch the response of execution 
	 * Step 5:- Display the response at the console 
	 */
	public static void main(String[] args) {
		/*HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
		try(CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse response = client.execute(get))
		{
			StatusLine status = response.getStatusLine();
			System.out.println(status.getStatusCode());
			System.out.println(status.getProtocolVersion());
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			String getBody = body.handleResponse(response);
			System.out.println(getBody);
			System.out.println(restResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	/*	RestResponse response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/ping/hello",null);
		System.out.println(response.toString());
		String url = "http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		response = RestApiHelper.performGetRequest(url,headers);*/
		
		String jsonBody = "{" +
				"\"BrandName\": \"Dell\"," +
				"\"Features\": {" +
					"\"Feature\": [\"8GB RAM\"," +
					"\"1TB Hard Drive\"]"+
				"}," +
				"\"Id\": " + (int)(1000*(Math.random())) + "," +
				"\"LaptopName\": \"Latitude\"" +
			"}";
		
		
		/*HttpPost post = new HttpPost("http://localhost:8080/laptop-bag/webapi/api/add");
		try (CloseableHttpClient client = HttpClientBuilder.create().build()){
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			//StringEntity data = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
			//post.setEntity(data);
			File file = new File("TestDataFile");
			FileEntity data = new FileEntity(file, ContentType.APPLICATION_JSON);
			post.setEntity(data);
			CloseableHttpResponse response = client.execute(post);
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restResponse.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		/*Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		File file = new File("TestDataFile");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", file,ContentType.APPLICATION_JSON, headers);
		System.out.println(response.getStatusCode());
		System.out.println(response.getResponseBody());*/
		/*HttpUriRequest delete = RequestBuilder.delete("http://localhost:8080/laptop-bag/webapi/api/delete/129").build();
		try (CloseableHttpClient client = HttpClientBuilder.create().build();
			 CloseableHttpResponse response = client.execute(delete)){
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*RestResponse response = RestApiHelper.performDeleteRequest("http://localhost:8080/laptop-bag/webapi/api/delete/127", null);
		System.out.println(response.toString());*/
		
		String xmlBody = "<Laptop>" + "<BrandName>Dell</BrandName>"
				+ "<Features>" + "<Feature>8GB RAM</Feature>"
				+ "<Feature>1TB Hard Drive</Feature>"
				+ "<Feature>15.5 inch LCD</Feature>"
				+ "<Feature>1024 GB of SSD</Feature>"
				+ "<Feature>4GB of Graphic card</Feature>"
				+ "<Feature>This is Put</Feature>" + "</Features>"
				+ "<Id>656</Id>" + "<LaptopName>Latitude S Series</LaptopName>"
				+ "</Laptop>";
		
		/*RequestBuilder buildPut = RequestBuilder.put("http://localhost:8080/laptop-bag/webapi/api/update").setHeader("Content-Type", "application/xml").
		setHeader("Accept", "application/xml");
		HttpUriRequest put = buildPut.setEntity(new StringEntity(xmlBody, ContentType.APPLICATION_XML)).build();
		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(put)){
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/xml");
		RestResponse response = RestApiHelper.performPutResquest("http://localhost:8080/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		System.out.println(response.toString());*/
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		RestResponse response = HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8080/laptop-bag/webapi/api/all", headers);
		System.out.println(response.toString());
		
	}
}
