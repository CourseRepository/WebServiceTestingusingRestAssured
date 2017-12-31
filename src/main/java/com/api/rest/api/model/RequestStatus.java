package com.api.rest.api.model;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public class RequestStatus implements FutureCallback<HttpResponse> {

	@Override
	public void completed(HttpResponse result) {
		System.out.println("----------------Request completed------------" + result.getProtocolVersion());
	}

	@Override
	public void failed(Exception ex) {
		System.out.println("----------------Request failed------------" + ex.getMessage());
	}

	@Override
	public void cancelled() {
		System.out.println("----------------Request cancelled------------");
	}

}
