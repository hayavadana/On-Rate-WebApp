package com.hayavadana.rating.webservice.rest.json;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.MovieBean;
import com.hayavadana.rating.service.AddressService;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.CommonService;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;
import com.hayavadana.rating.webservice.rest.vo.MovieVO;

@Component
@Path("/movieService")
public class movieRestService {
	private static final Logger logger = Logger.getLogger(movieRestService.class);

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CommonService commonService;
	
	@GET
	@Path("movieDetails/movie/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public MovieVO getMovieDetails(@PathParam("movieId")String movieId){
		MovieBean movieBean = new MovieBean();
		MovieVO movieVO = new MovieVO();
		movieBean.setMovieId(Integer.valueOf(movieId));
		
		movieVO =  commonService.getMovieVODetails(movieBean);
		
		return movieVO;
	} 
	
	@POST
	@Path("rating/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserRating(BusinessBean businessBean){
		System.out.println("Input data from Client : "+businessBean.toString());
		
		return Response.status(200).entity(businessBean).build();
	}

	@GET
	@Path("/movieList/language/{language}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovieVO> getMovieListsByLanguage(@PathParam("language")String language){
		logger.info("BusinessRestService --getBusinessListsByCountryStateCityAndCategory -- Country : "+language);

		System.out.println("BusinessRestService --getBusinessListsByCountryStateCityAndCategory -- Country : "+language);

		List<MovieVO> getMovieVOList =  commonService.getMovieVOList(language);
				
		return getMovieVOList;
	}
	
}
