package com.api.rest.api.restassuredhelper.mocks;

import java.net.URL;

public class ResourceHelper {
	public String getFilePath(String fileName) {
		URL in = ResourceHelper.class.getResource("/" + fileName);
		if (in != null) {
			String path = in.getPath();
			if(!path.isEmpty()) {
				path = path.replaceFirst("/", "");
				System.out.println(path);
				return path;
			}
		}
		throw new RuntimeException(String.format("File Not Found %s", fileName));
	}

}
