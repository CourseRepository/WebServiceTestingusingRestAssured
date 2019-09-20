package com.api.rest.api.restassuredhelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.api.rest.api.helper.Features;
import com.api.rest.api.helper.LaptopBag;

import io.restassured.http.ContentType;

/**
* rathr1 
**/
@RunWith(Parameterized.class) // 1st Step
public class TestPostWithDataDriven extends BaseClass {
	
	/* Use case :- Can we pass multiple set of data when doing post with object mapper. */
	
	// 2nd Step
	/*@Parameter(value = 1)
	public int parameterOne;
	@Parameter(value = 0)
	public int parameterTwo;*/
	
	// 2 rows -> 2 set of data
	// 2 column -> 2 parameter
	//3rd Step
/*	@Parameters
	public static Collection<Object[]> getData() {
		Object[][] data = new Object[][]{
			{1,2},
			{3,4}
		};
		return Arrays.asList(data);
	}
	*/
	@Parameter
	public LaptopBag bag;
	
	@Parameters
	public static ArrayList<LaptopBag> getTestDataList() {
		return getLapTopBagObject();
	}
	
	@Test
	public void testPostWithDynamicData(){
		//System.out.println(parameterOne + ":" + parameterTwo);
		//ArrayList<LaptopBag> testData =  getLapTopBagObject();
		given()
		.log()
		.body()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Laptop.Id", equalTo(bag.getId()));
	}
	
	// 1 read the data from file 
	// 2 Store the data in a array list
	// 3 Create the object of laptop bag class
	// 4 Create the object of laptop bag class for every entry inside the array list
	// 5 the use the Parameterized concept to pass the object to the post request
	
	private static ArrayList<LaptopBag> getLapTopBagObject() {
		ArrayList<LaptopBag> laptopBagList = new ArrayList<>();
		ArrayList<String> testData =  getTestData();
		
		for (String entry : testData) {
			if(null != entry){
				laptopBagList.add(getLaptopBag(entry));
			}
		}
		return laptopBagList;
	}
	
	private static LaptopBag getLaptopBag(String entry) {
		LaptopBag aBag = new LaptopBag();
		String aData[] = entry.split(",");
		aBag.setBrandName(aData[0]);
		aBag.setId(aData[aData.length -2]);
		aBag.setFeatures(getFeatures(aData));
		aBag.setLaptopName(aData[aData.length - 1]);
		return aBag;
	}
	
	private static Features getFeatures(String[] aData) {
		Features features = new Features();
		ArrayList<String> feature = new ArrayList<>();
		
		for (int i = 1; i< aData.length - 2; i++) {
			if(null != aData[i]){
				feature.add(aData[i]);
			}
		}
		features.setFeature(feature);
		return features;
	}

	private static ArrayList<String> getTestData() {
		ArrayList<String> testData = new ArrayList<>();
		String str = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("C:\\Users\\rathr1\\Desktop\\TestData.txt")));
			do {
				str = in.readLine();
				if( null != str)
					testData.add(str);
			} while (str != null);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}
	
}
