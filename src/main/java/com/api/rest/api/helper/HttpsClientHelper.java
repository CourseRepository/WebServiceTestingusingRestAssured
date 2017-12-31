package com.api.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.api.rest.api.model.RestResponse;

public class HttpsClientHelper {
	
	private static HttpEntity getHttpEntity(Object content,ContentType type) {
		if(content instanceof String)
			return new StringEntity((String)content, type);
		else if (content instanceof File) 
			return new FileEntity((File)content, type);
		else 
			throw new RuntimeException("Entity Type not found ");
		
	}
	
	private static Header[] getCustomHeaders(Map<String, String> headers) {
		Header[] customHeaders = new Header[headers.size()];
		int i = 0;
		for (String key : headers.keySet()) {
			customHeaders[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
		
	}
	
	public static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException  {
		
		TrustStrategy trust = new TrustStrategy() {
			
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				return true;
			}
		};
		return SSLContextBuilder.create().loadTrustMaterial(trust).build();
	}
	
	public static CloseableHttpClient getHttpClient(SSLContext sslContext) {
		return HttpClientBuilder.create().setSSLContext(sslContext).build();
	}
	
	
	private static RestResponse performRequest(HttpUriRequest method) {
		CloseableHttpResponse response = null;
		try(CloseableHttpClient client = getHttpClient(getSSLContext())) {
			response = client.execute(method);
			ResponseHandler<String> handler = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		} catch (Exception e) {
			if(e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(),e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	public static RestResponse performGetRequestWithSSL(String uri,Map<String, String> headers) {
		try {
			return performGetRequestWithSSL(new URI(uri),headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performGetRequestWithSSL(URI uri,Map<String, String> headers) {
		HttpGet get = new HttpGet(uri);
		if(null != headers)
			get.setHeaders(getCustomHeaders(headers));
		return performRequest(get);
	}
	
	public static RestResponse performPostWithSSL(URI uri,Object content,ContentType type,Map<String, String> headers) {
		HttpUriRequest post = RequestBuilder.post(uri).setEntity(getHttpEntity(content, type)).build();
		if(null != headers)
			post.setHeaders(getCustomHeaders(headers));
		return performRequest(post);
	}
	
	public static RestResponse performPostWithSSL(String uri,Object content,ContentType type,Map<String, String> headers) {
		try {
			return performPostWithSSL(new URI(uri),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPutRequestWithSSL(URI uri,Object content,ContentType type,Map<String, String> headers) {
		HttpUriRequest put = RequestBuilder.put(uri).setEntity(getHttpEntity(content, type)).build();
		if(null != headers)
			put.setHeaders(getCustomHeaders(headers));
		return performRequest(put);
	}
	
	public static RestResponse performPutRequestWithSSL(String uri,Object content,ContentType type,Map<String, String> headers) {
		try {
			return performPutRequestWithSSL(new URI(uri),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performDeleteRequestWithSSL(URI uri,Map<String, String> headers) {
		HttpUriRequest delete = RequestBuilder.delete(uri).build();
		if(null != headers)
			delete.setHeaders(getCustomHeaders(headers));
		return performRequest(delete);
	}
	
	public static RestResponse performDeleteRequestWithSSL(String uri,Map<String, String> headers) {
		try {
			return performDeleteRequestWithSSL(new URI(uri),headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

}
