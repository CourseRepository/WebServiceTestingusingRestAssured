package com.api.rest.api.questions;

import org.testng.annotations.Test;

import com.api.rest.api.questions.model.Root;
import com.google.gson.Gson;

/**
* rathr1
* 
**/
public class DeSerializeJson {

	private String body = "{\"cashcard\":[{\"bin\":\"string\",\"cashCardNo\":\"string\",\"groupNO\":\"string\",\"id\":0,\"pcn\":\"string\" }],\"consumers\":[{\"addresses\":[{\"address1\":\"string\",\"address2\":\"string\",\"addressGuid\":\"string\",\"city\":\"string\",\"country\":\"string\",\"ePostId\":0,\"name\":\"string\",\"primaryBilling\":true,\"primaryShipping\":true,\"state\":\"string\","
			+ "\"type\":\"string\",\"zipcode\":\"string\"}],\"birthDate\":\"2019-12-01T20:35:05.618Z\",\"consumerGuid\":\"string\",\"email\":\"string\",\"emailVerified\":true,\"firstName\":\"string\",\"genderCode\":\"M\",\"lastName\":\"string\",\"maritalStatusCode\":\"MARRIED\","
			+ "\"members\":[{\"altAccountIdent\":\"string\",\"code\":\"string\",\"familyPosition\":\"string\",\"insuredNo\":\"string\","
			+ "\"memberNo\":\"string\",\"relationshipCode\":\"string\",\"xRefMemberNo\":\"string\"}],\"middleName\":\"string\","
			+ "\"phones\":[{\"extension\":\"string\",\"name\":\"string\",\"phoneGuid\":\"string\",\"phoneNo\":\"string\",\"primary\":true,"
			+ "\"type\":\"string\"}]}],\"credentials\":[{\"credentialType\":\"OKTA\",\"credentialValue\":\"string\",\"obsolete\":true}],"
			+ "\"guid\":\"string\"}";
	
	@Test
	public void testDeSerializeJson() {
		Gson gson = new Gson();
		Root root = gson.fromJson(body, Root.class);
		System.out.println(root.toString());
	}
}
