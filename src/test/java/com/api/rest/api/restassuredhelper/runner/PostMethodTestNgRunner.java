package com.api.rest.api.restassuredhelper.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/java/com/api/rest/api/restassuredhelper/featurefile/PostMethod.feature",
				"src/test/java/com/api/rest/api/restassuredhelper/featurefile/PutMethod.feature"},
		glue = {"com.api.rest.api.restassuredhelper.stepdfn","com.api.rest.api.restassuredhelper.hooks"},
		monochrome = true,
		dryRun = false
		)
public class PostMethodTestNgRunner extends AbstractTestNGCucumberTests {

}
