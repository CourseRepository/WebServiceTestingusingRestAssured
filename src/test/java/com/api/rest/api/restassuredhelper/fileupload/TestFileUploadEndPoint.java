package com.api.rest.api.restassuredhelper.fileupload;

import org.apache.http.HttpStatus;
import org.junit.Test;
import static io.restassured.RestAssured.*;

import java.io.File;

public class TestFileUploadEndPoint {
	
	@Test
	public void test_file_upload() {
		File uploadFile = new File("C:\\Users\\rathr1\\Desktop\\PipeLine-Command.txt");
		
		given()
		.log()
		.all()
		.multiPart("file", uploadFile, "multipart/form-data")
		.when()
		.post("https://jobportalkarate.herokuapp.com/normal/webapi/upload")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
		
	}

}
