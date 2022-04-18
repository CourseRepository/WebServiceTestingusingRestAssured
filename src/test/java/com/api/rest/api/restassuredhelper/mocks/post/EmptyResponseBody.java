package com.api.rest.api.restassuredhelper.mocks.post;

import org.json.JSONObject;

public class EmptyResponseBody implements AbstractResponseBodyProvider {

	@Override
	public JSONObject getResponseBody() {
		return new JSONObject();
	}

}
