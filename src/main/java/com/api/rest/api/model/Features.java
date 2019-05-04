package com.api.rest.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Features {
	
	private ArrayList<String> Feature;
	
	@JsonProperty("Feature")
	public List<String> getFeature() {
		return Feature;
	}

	public void setFeature(ArrayList<String> feature) {
		Feature = feature;
	}

}
