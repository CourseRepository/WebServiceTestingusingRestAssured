package com.api.rest.api.restassuredhelper.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/com/api/rest/api/restassuredhelper/featurefile/PostMethod.feature",
				"src/test/java/com/api/rest/api/restassuredhelper/featurefile/PutMethod.feature"},
		glue = {"com.api.rest.api.restassuredhelper.stepdfn","com.api.rest.api.restassuredhelper.hooks"},
		monochrome = true,
		dryRun = false
		)
public class PostMethodRunner {

}
