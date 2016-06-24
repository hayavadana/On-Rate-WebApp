package com.hayavadana.rating.webservice.rest.json;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.service.RateService;
import com.hayavadana.rating.service.impl.BusinessServiceImpl;
import com.hayavadana.rating.webservice.rest.vo.movieRateVO;

@Transactional
@Component
@Path("/userRating")
public class UserRatingService {
	private static final Logger logger = Logger.getLogger(UserRatingService.class);

	@Autowired
    private RateService rateService;
	
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserRate(RateVO rate){
		String output = null;
		try{
			logger.info("UserRatingSe "
					+ "rvice --saveUserRate -- before calling Service ");

			 output = rate.toString();
			System.out.println("-----You are in Consume service : "+output);

			rateService.saveRate(rate);
			
			logger.info("UserRatingService --saveUserRate -- after calling Service ");
	
		}catch(Exception ex)
		{
			System.out.println("UserRatingService  -- saveUserRate -- Exception : "+ex);
		}
		
		return Response.status(200).entity(output).build();
	}
	
	/*@GET
	@Path("getReviewComments/business/{business_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RateVO> getReviewComments(@PathParam("business_id")String businessId){
		RateVO rateVO = new RateVO();
		
		System.out.println("----------------You are in gerReviewComments......");
		
		RateVO rate =new RateVO();
		rate.setEmailId("hari@gmail.com");
		rate.setPhoneNumber("9849065675");
		rate.setUserComments("Very Excellent");
		rate.setUserRate(5);

		RateVO rate1 =new RateVO();
		rate1.setEmailId("vinay@gmail.com");
		rate1.setPhoneNumber("9849065675");
		rate1.setUserComments("Very Good");
		rate1.setUserRate(4);

		RateVO rate2 =new RateVO();
		rate2.setEmailId("sreedhar@gmail.com");
		rate2.setPhoneNumber("9849065675");
		rate2.setUserComments("awesome");
		rate2.setUserRate(5);

		
		ArrayList<RateVO> reviewList = new ArrayList<RateVO>();
		reviewList.add(rate);
		reviewList.add(rate1);
		reviewList.add(rate2);
		
		RateService rateService  = null;
		
		return reviewList;
	}*/
	
	@GET
	@Path("getReviewComments/business/{business_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RateVO> getReviewComments(@PathParam("business_id")String businessId){
		RateVO rateVO = new RateVO();
		List<RateVO> rateList = null;
		
		rateList = rateService.getUserComments(Integer.parseInt(businessId));
			
		return rateList;
	}
	//--------------movie------------//
	@GET
	@Path("getReviewComments/movie/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<movieRateVO> getMovieReviewComments(@PathParam("movieId")String movieId){
		movieRateVO rateVO = new movieRateVO();
		rateVO.setMovieId(Integer.valueOf(movieId));
		
		System.out.println("----------------You are in getMovieReviewComments......");
		
		
		List<movieRateVO> reviewList = null;
		
		reviewList=rateService.getMovieUserRateList(rateVO);
		
		return reviewList;
	}
	
	@POST
	@Path("/movie/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveMovieUserRate(movieRateVO rate){
		String output = null;
		try{
			logger.info("UserRatingService --saveUserRate -- before calling Service ");

			 output = rate.toString();
			System.out.println("-----You are in Consume service : "+output);

			rateService.saveMovieRate(rate);
			
			logger.info("UserRatingService --saveUserRate -- after calling Service ");
	
		}catch(Exception ex)
		{
			System.out.println("UserRatingService  -- saveUserRate -- Exception : "+ex);
		}
		
		return Response.status(200).entity(output).build();
	}
}
