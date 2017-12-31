package com.api.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterModel {
	
	@JsonProperty("id")
	public long id;
	
	@JsonProperty("id_str")
	public String id_Str;
}
