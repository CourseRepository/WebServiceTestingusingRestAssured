package com.api.rest.api.helper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Laptop")
public class LaptopBag {
	
	private String brandName;
	@XmlElement(name="BrandName")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	@XmlElement(name="Id")
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	@XmlElement(name="LaptopName")
	public String getLaptopName() {
		return laptopName;
	}
	public void setLaptopName(String laptopName) {
		this.laptopName = laptopName;
	}
	@XmlElement(name="Features")
	public Features getFeatures() {
		return Features;
	}
	public void setFeatures(Features features) {
		Features = features;
	}
	private String Id;
	private String laptopName;
	private Features Features;

}
