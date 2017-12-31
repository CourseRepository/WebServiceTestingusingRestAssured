package com.api.rest.api.restassuredhelper.stepdfn;

import java.util.List;

import com.api.rest.api.restassuredhelper.transform.TransformData;
import com.api.util.RestModel;
import com.api.util.RestUtil;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PutMethodStepDfn {
	
	private RestModel restModel;
	private String id; // To Store the id in response
	
	public PutMethodStepDfn(RestModel restModel) {
		this.restModel = restModel;
	}
	
	
	@When("^I perform the PUT request with id and Details \"([^\"]*)\"$")
	public void i_perform_the_PUT_request_with_id_and_Details(@Transform(TransformData.class) List<String> data) throws Throwable {
		id = restModel.response.thenReturn().jsonPath().getString("Id");
		String body = RestUtil.getJsonBody(data.get(0), new Integer(id), data.get(data.size()-1), data.subList(1, data.size()-1));
		restModel.response = restModel.requestSpecification.body(body).put("/update");
		
	}
	
	@Then("^Details should get updated$")
	public void details_should_get_updated() throws Throwable {
		System.out.println("Put ==> " + restModel.response.asString());
	}
}
