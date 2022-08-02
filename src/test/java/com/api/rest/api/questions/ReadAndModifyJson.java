package com.api.rest.api.questions;

import org.json.JSONObject;
import org.junit.Test;

public class ReadAndModifyJson {

	@Test
	public void updateJson() {
		String inputJson = "{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       { \r\n"
				+ "          \"key\": \"RAT\"\r\n" + "       },\r\n"
				+ "       \"summary\": \"Creating Issue form REST Automation Framework.\",\r\n"
				+ "       \"description\": \"Creating issue form REST Automation Framework\",\r\n"
				+ "       \"issuetype\": {\r\n" + "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "   }\r\n" + "}";
		
		JSONObject inputJsonObject = new JSONObject(inputJson);
		System.err.println("--------------- JSON before Modification --------------");
		System.out.println(inputJson);
		inputJsonObject.put("description", "REST Automation Framework create the BUG-127867");
		String updatedJson = inputJsonObject.toString();
		System.err.println("--------------- JSON After Modification --------------");
		System.out.println(updatedJson);
	}
}
