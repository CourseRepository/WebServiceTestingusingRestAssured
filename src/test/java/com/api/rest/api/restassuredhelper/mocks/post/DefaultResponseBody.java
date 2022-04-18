package com.api.rest.api.restassuredhelper.mocks.post;

import java.util.Arrays;

import org.json.JSONObject;

public class DefaultResponseBody implements AbstractResponseBodyProvider {

	@Override
	public JSONObject getResponseBody() {
		JSONObject laptopBag = new JSONObject();
		laptopBag.put("BrandName", "Alienware");
		laptopBag.put("Id", 100);
		laptopBag.put("LaptopName", "Alienware M17");

		JSONObject featureObj = new JSONObject();
		featureObj.put("Feature",
				Arrays.asList("8th Generation Intel® Core™ i5-8300H", "Windows 10 Home 64-bit English",
						"NVIDIA® GeForce® GTX 1660 Ti 6GB GDDR6", "8GB, 2x4GB, DDR4, 2666MHz"));
		laptopBag.put("Features", featureObj);
		return laptopBag;
	}

}
