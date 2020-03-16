package com.api.rest.api.ssl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.junit.Test;

import io.restassured.http.ContentType;


public class TestGetWithSSL extends BaseSSLClass  {
	
	@Test
	public void testGet() {
		
		String s = given()
				.relaxedHTTPSValidation()
		.accept(ContentType.XML)
		.when()
		.get("/all")
		.thenReturn()
		.asString();
		System.out.println(s);
		
		/**
		 * 1. To bypass the certificate check
		 * 2. Is to supply valid certificate along with request
		 * 
		 * */
		
	}
	
	@Test
	public void testGetWithCertificate() {
		
		given()
		.log()
		.all()
		.when()
		.get("/all")
		.then()
		.assertThat()
		.body("$", is(notNullValue()));
		
	}
	
	@Test
	public void testGetWithKeyStore(){
		KeyStore trustStore;
		try {
			trustStore = KeyStore.getInstance("PKCS12");
			trustStore.load(new FileInputStream(new File("<location to p12>")), "passwork".toCharArray());
			 given()
					.trustStore(trustStore)
			.accept(ContentType.XML)
			.when()
			.get("/all")
			.thenReturn()
			.asString();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
