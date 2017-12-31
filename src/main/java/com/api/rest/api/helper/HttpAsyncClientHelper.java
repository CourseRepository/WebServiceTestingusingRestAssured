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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.ssl.SSLContextBuilder;

import com.api.rest.api.model.RequestStatus;
import com.api.rest.api.model.RestResponse;

public class HttpAsyncClientHelper {
	
	
	private static Header[] getCustomHeaders(Map<String, String> headers) {
		Header[] customHeaders = new Header[headers.size()];
		int i = 0;
		for (String key : headers.keySet()) {
			customHeaders[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
		
	}
	
	private static HttpEntity getHttpEntity(Object content,ContentType type) {
		if(content instanceof String)
			return new StringEntity((String)content, type);
		else if (content instanceof File) 
			return new FileEntity((File)content, type);
		else 
			throw new RuntimeException("Entity Type not found ");
		
	}
	
	private static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy trust = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				return true;
			}
		};
		return SSLContextBuilder.create().loadTrustMaterial(trust).build();
	}
	
	private static CloseableHttpAsyncClient getHttpAsyncClient(SSLContext context) throws IOReactorException {
		if(context == null)
			return HttpAsyncClientBuilder.create().build();
		return HttpAsyncClientBuilder.create().setSSLContext(context).build();
	}
	
	private static RestResponse performRequest(HttpUriRequest method,SSLContext context) throws InterruptedException, ExecutionException {
		Future<HttpResponse> response = null;
		try(CloseableHttpAsyncClient client = getHttpAsyncClient(context)) {
			client.start();
			response = client.execute(method, new RequestStatus());
			ResponseHandler<String> handler = new BasicResponseHandler();
			return new RestResponse(response.get().getStatusLine().getStatusCode(), handler.handleResponse(response.get()));
		} catch (Exception e) {
			if(e instanceof HttpResponseException)
				return new RestResponse(response.get().getStatusLine().getStatusCode(),e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performGetRequestAsync(String uri,Map<String, String> headers) {
		try {
			return performGetRequestAsync(new URI(uri),headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performGetRequestAsync(URI uri,Map<String, String> headers) {
		HttpGet get = new HttpGet(uri);
		if(headers != null)
			get.setHeaders(getCustomHeaders(headers));
		try {
			return performRequest(get,null);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPostRequestAsync(URI uri,Object content,ContentType type,Map<String, String> headers) {
		HttpPost post = new HttpPost(uri);
		if(headers != null)
			post.setHeaders(getCustomHeaders(headers));
		post.setEntity(getHttpEntity(content, type));
		try {
			return performRequest(post,null);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPostRequestAsync(String uri,Object content,ContentType type,Map<String, String> headers) {
		try {
			return performPostRequestAsync(new URI(uri),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPutRequestAsync(URI uri,Object content,ContentType type,Map<String, String> headers) {
		HttpPut post = new HttpPut(uri);
		if(headers != null)
			post.setHeaders(getCustomHeaders(headers));
		post.setEntity(getHttpEntity(content, type));
		try {
			return performRequest(post,null);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPutRequestAsync(String uri,Object content,ContentType type,Map<String, String> headers) {
		try {
			return performPutRequestAsync(new URI(uri),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performDeleteRequestAsync(URI uri,Map<String, String> headers) {
		HttpDelete post = new HttpDelete(uri);
		if(headers != null)
			post.setHeaders(getCustomHeaders(headers));
		try {
			return performRequest(post,null);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performDeleteRequestAsync(String uri,Map<String, String> headers) {
		try {
			return performDeleteRequestAsync(new URI(uri),headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performGetSSLRequestAsync(String uri,Map<String, String> headers) {
		try {
			return performGetRequestAsync(new URI(uri),headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performGetSSLRequestAsync(URI uri,Map<String, String> headers) {
		HttpGet get = new HttpGet(uri);
		if(headers != null)
			get.setHeaders(getCustomHeaders(headers));
		try {
			return performRequest(get,getSSLContext());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPostSLLRequestAsync(URI uri,Object content,ContentType type,Map<String, String> headers) {
		HttpPost post = new HttpPost(uri);
		if(headers != null)
			post.setHeaders(getCustomHeaders(headers));
		post.setEntity(getHttpEntity(content, type));
		try {
			return performRequest(post,getSSLContext());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPostSSLRequestAsync(String uri,Object content,ContentType type,Map<String, String> headers) {
		try {
			return performPostRequestAsync(new URI(uri),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPutSSLRequestAsync(URI uri,Object content,ContentType type,Map<String, String> headers) {
		HttpPut post = new HttpPut(uri);
		if(headers != null)
			post.setHeaders(getCustomHeaders(headers));
		post.setEntity(getHttpEntity(content, type));
		try {
			return performRequest(post,getSSLContext());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPutSSLRequestAsync(String uri,Object content,ContentType type,Map<String, String> headers) {
		try {
			return performPutRequestAsync(new URI(uri),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performDeleteSSLRequestAsync(URI uri,Map<String, String> headers) {
		HttpDelete post = new HttpDelete(uri);
		if(headers != null)
			post.setHeaders(getCustomHeaders(headers));
		try {
			return performRequest(post,getSSLContext());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performDeleteSSLRequestAsync(String uri,Map<String, String> headers) {
		try {
			return performDeleteRequestAsync(new URI(uri),headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}


}
