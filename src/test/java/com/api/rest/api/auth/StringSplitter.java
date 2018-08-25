package com.api.rest.api.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringSplitter {
	
	private SeperatorType[] types;
	
	public StringSplitter(SeperatorType ...types) {
		this.types = types;
	}
	
	public List<String> split(String str){
		String token = getToken(types);
		List<String> list = getSubList(str,token);
		return list;
	}

	private List<String> getSubList(String str,String delim) {
		List<String> strList = new ArrayList<>();
		
		strList.addAll((Arrays.asList(str.split(delim))));
		
		
		return strList;
	}

	private String getToken(SeperatorType[] types) {
		if(null == types || types.length < 1)
			throw new RuntimeException("Invalid Token");
		
		String token = "";
		for(SeperatorType type : types){
			token = token + type.getValue();
		}
		return token;
	}
	
	public static void main(String[] args) {
		String str = "This is rahul:;Hi every one. Helloe::This is :;:; Everyone";
		StringSplitter split = new StringSplitter(SeperatorType.COLON);
		List<String> s = split.split(str);
		for(String string : s)
			System.out.println(string);
	}

}

enum SeperatorType{
	COMMA(","),
	SEMICOLON(";"),
	COLON(":");
	
	String value;
	
	SeperatorType(String value){
		this.value = value;
	}
	
	String getValue(){
		return this.value;
	}
}
