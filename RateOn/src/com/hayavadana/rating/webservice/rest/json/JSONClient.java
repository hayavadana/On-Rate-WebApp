package com.hayavadana.rating.webservice.rest.json;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JSONClient {
	static final String REST_URI = "http://localhost:8080/Rating/rest/json/emp/";
	 public static void main(String args[]){
		 ClientConfig config = new DefaultClientConfig();
		 Client client = Client.create(config);
		 
		 WebResource service = client.resource(REST_URI).path("Hari");
		 String msg = service.accept(MediaType.APPLICATION_JSON).get(String.class);
		 
		 System.out.println(msg);
		 
	 }
}
