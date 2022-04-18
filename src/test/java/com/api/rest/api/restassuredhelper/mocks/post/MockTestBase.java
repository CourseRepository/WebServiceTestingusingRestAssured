package com.api.rest.api.restassuredhelper.mocks.post;

import com.github.tomakehurst.wiremock.client.WireMock;

public abstract class MockTestBase {
	
	private AbstractStubProvider stubProvider;

	public void setStubProvider(AbstractStubProvider stubProvider) {
		this.stubProvider = stubProvider;
	}
	
	protected void registerStub() {
		WireMock.stubFor(stubProvider.getStub());
	}

}
