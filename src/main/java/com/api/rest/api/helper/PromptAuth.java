package com.api.rest.api.helper;

import com.api.rest.api.model.RestResponse;

public class PromptAuth {

	public static void main(String[] args) {
		/*CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin1", "admin1"));
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(provider);
		
		HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/prompt/all");
		
		try(CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(get, context)) {
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		RestResponse response = HttpsClientHelper.performGetRequestWithSSL("https://localhost:8443/laptop-bag/webapi/sslres/all", null);
		System.out.println(response.toString());

	}

}
