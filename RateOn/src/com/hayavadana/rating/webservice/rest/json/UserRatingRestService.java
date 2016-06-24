package com.hayavadana.rating.webservice.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.webservice.rest.vo.RateVO;

@Transactional
@Component
@Path("/userRateService")
public class UserRatingRestService {

	@POST
	@Path("rate/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserRating(RateVO rateVO){
		System.out.println("Input data from Client : "+rateVO.toString());
		
		return Response.status(200).entity(rateVO).build();
	}
}
