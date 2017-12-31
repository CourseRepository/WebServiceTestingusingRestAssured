package com.api.rest.api.restassuredhelper.stepdfn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import com.api.rest.api.restassuredhelper.transform.TransformData;
import com.api.util.RestModel;
import com.api.util.RestUtil;

import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;

public class PostMethodStepDfn {
	
	/*private RequestSpecification requestSpecification;
	private Response response;*/
	
	private String jsonBody ;
	private RestModel restModel;
	
	 public PostMethodStepDfn(RestModel restModel){
		this.restModel = restModel;
	}
	
	@Given("^Accept the content in Json Format$")
	public void accept_the_content_in_Json_Format() throws Throwable {
		restModel.requestSpecification = given().accept(ContentType.JSON);
	}

	@And("^Content Type as JSON$")
	public void content_Type_as_JSON() throws Throwable {
		restModel.requestSpecification = restModel.requestSpecification.contentType(ContentType.JSON);
	}

	@When("^I perform the Post request with BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
	public void i_perform_the_Post_request_with_BrandName_as_Features_as_LaptopName_as(String brandName, String feature, String laptopName) throws Throwable {
	    String body = RestUtil.getJsonBody(brandName, null, laptopName, Arrays.asList(feature.split(",")));
	    restModel.response = restModel.requestSpecification.body(body).post("/add");
	}
	
	@When("^I perform the Post request with details \"([^\"]*)\"$")
	public void i_perform_the_Post_request_with_details(@Transform(TransformData.class)List<String> data) throws Throwable {
		 String body = RestUtil.getJsonBody(data.get(0), null, data.get(data.size()-1), data.subList(1, data.size()-1));
		 restModel.response = restModel.requestSpecification.body(body).post("/add");
	}
	

	@Then("^Status code \"([^\"]*)\" should returned$")
	public void status_code_should_returned(String statusCode) throws Throwable {
		restModel.response.then().assertThat().statusCode(Integer.parseInt(statusCode));
	}

	@And("^Response should have integer Id$")
	public void response_should_have_integer_Id() throws Throwable {
		restModel.response.then().assertThat().body("Id", is(Integer.class));
	}
	
	@But("^I supply invalid json body$")
	public void i_supply_invalid_json_body() throws Throwable {
	    jsonBody = "Invalid body";
	}

	@When("^I perform the Post request$")
	public void i_perform_the_Post_request() throws Throwable {
		restModel.response = restModel.requestSpecification.body(jsonBody).post("/add");
	}

}
