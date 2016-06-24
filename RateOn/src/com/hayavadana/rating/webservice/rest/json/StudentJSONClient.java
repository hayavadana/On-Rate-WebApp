package com.hayavadana.rating.webservice.rest.json;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentJSONClient {

	public static void main(String args[]){
		try{

			 String input = "{\"id\":4,\"firstName\":\"Hari\",\"lastName\":\"Tech\",\"age\":40}";
			 
			 Client client = Client.create();
			 
			 WebResource service = client.resource("http://localhost:8080/Rating/rest/userRating/send");
			 
			 ClientResponse response = service.type("application/json").post(ClientResponse.class,input);
			 
			 if(response.getStatus() != 200){
				 throw new RuntimeException("Failed : HTTP error code : "+response.getStatus());
			 }
			 
			 System.out.println("Output from Server .... \n");
			 String output = response.getEntity(String.class);
			 System.out.println(output);
	

		}catch (Exception e) {
			 e.printStackTrace();
		}
	}

}
