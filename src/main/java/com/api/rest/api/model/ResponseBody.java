package com.api.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {
	private String BrandName;
	
	@JsonProperty("BrandName")
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	@JsonProperty("Id")
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	@JsonProperty("LaptopName")
	public String getLaptopName() {
		return LaptopName;
	}
	public void setLaptopName(String laptopName) {
		LaptopName = laptopName;
	}
	@JsonProperty("Features")
	public Features getFeatures() {
		return Features;
	}
	public void setFeatures(Features features) {
		Features = features;
	}
	private String Id;
	private String LaptopName;
	private Features Features;
	
}

/*class Features{
	private List<String> Feature = new ArrayList<String>();
	
	@JsonProperty("Feature")
	public List<String> getFeature() {
		return Feature;
	}

	public void setFeature(List<String> feature) {
		Feature = feature;
	}
}*/