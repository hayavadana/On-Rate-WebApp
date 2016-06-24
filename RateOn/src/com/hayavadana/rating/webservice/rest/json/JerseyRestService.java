package com.hayavadana.rating.webservice.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Path("/jsonServices")
public class JerseyRestService {

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON(RateVO student){
		String output = student.toString();
		System.out.println("-----You are in Consume service : "+output);
		return Response.status(200).entity(output).build();
	}
}
