package com.api.rest.api.questions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.junit.Test;

import com.api.rest.api.model.RequestStatus;

public class HttpAsyncFileUploadTest {

	CloseableHttpAsyncClient asyncClient = HttpAsyncClientBuilder.create().build();
	ResponseHandler<String> handler = new BasicResponseHandler();
	Future<HttpResponse> responseOne = null;
	Future<HttpResponse> responseTwo = null;
	
	@Test
	public void uploadFile() throws IOException, InterruptedException, ExecutionException {
		String responseData = "";
		try {
			asyncClient.start();
			HttpPost requestOne = getUploadRequest();
			HttpPost requestTwo = getUploadRequest();
			responseOne = asyncClient.execute(requestOne, new RequestStatus());
			responseTwo = asyncClient.execute(requestTwo, new RequestStatus());
			handler.handleResponse(responseOne.get());
			handler.handleResponse(responseTwo.get());
		} finally {
			asyncClient.close();
		}
		System.out.println(responseData);
	}

	private HttpPost getUploadRequest() {
		HttpPost request = new HttpPost("https://jobportalkarate.herokuapp.com//normal/webapi/upload");
		request.addHeader("Accept", "application/json");
		request.addHeader("Content-Type", "multipart/form-data");
		request.setEntity(getRequestBody());
		return request;
	}

	private HttpEntity getRequestBody() {
		HttpEntity reqestBody = MultipartEntityBuilder
				.create()
				.addBinaryBody("file", new File("C:\\Data\\log\\MouseMove.java"), ContentType.MULTIPART_FORM_DATA, "MouseMove.java")
				.build();
		return reqestBody;
	}

}
