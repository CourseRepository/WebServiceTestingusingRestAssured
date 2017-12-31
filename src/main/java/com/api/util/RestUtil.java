package com.api.util;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class RestUtil {
	
	private static final String BRANDNAME = "BrandName";
	private static final String FEATURES = "Features";
	private static final String FEATURE = "Feature";
	private static final String _Id = "Id";
	private static final String LAPTOPNAME = "LaptopName";
	
	private static int getRandomId() {
		return (int)(1000*(Math.random()));
	}
	
	public static String getJsonBody(String brandName,Integer id,String laptopName,List<String> feature) {
		JsonObject jsonObject = new JsonObject(); // parent
		
		jsonObject.addProperty(BRANDNAME, brandName);
		jsonObject.addProperty(_Id, id == null ? getRandomId() + "" : id + "");
		
		JsonObject featureObject = new JsonObject(); //child
		JsonArray array = getJsonArray(feature); // converting the string into a json arrary
		
		featureObject.add(FEATURE, array); //adding to child json object
		jsonObject.add(FEATURES, featureObject); // adding child json object to parent
		
		jsonObject.addProperty(LAPTOPNAME, laptopName);
		
		System.out.println(jsonObject.toString());
		
		return jsonObject.toString();
	}

	private static JsonArray getJsonArray(List<String> feature) {
		JsonArray array = new JsonArray();
		
		for (String jsonElement : feature) {
			array.add(jsonElement);
		}
		return array;
	}

}
