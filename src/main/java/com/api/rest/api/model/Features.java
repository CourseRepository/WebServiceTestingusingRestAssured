package com.api.rest.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

 class Features {
private List<String> Feature = new ArrayList<String>();
	
	@JsonProperty("Feature")
	public List<String> getFeature() {
		return Feature;
	}

	public void setFeature(List<String> feature) {
		Feature = feature;
	}

}
