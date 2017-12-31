package com.api.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterTokenModel {

	@JsonProperty("oauth_token")
	public String accessToken;
	
	@JsonProperty("oauth_token_secret")
	public String accessTokenSceret;
	
}
