package com.hayavadana.rating.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hayavadana.rating.service.CountryService;
import com.hayavadana.rating.bean.CountryBean;
import com.mkyong.transaction.TransactionBo;

import java.util.List;
import java.util.ArrayList;

@Component
@Path("/hello")
public class UserRestService {

	@Autowired
	private CountryService countryService;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		
		System.out.println("--------------UserRestService----------- ");
	List<CountryBean> countryList = countryService.getCountriesList();
		for(CountryBean ctryBean : countryList){
			System.out.println(ctryBean.getCountryCode()+"           " +ctryBean.getCountryDesc());
		}
		
		System.out.println("----------------------Testing-------------");
		return "Hello World RESTFul Jersey !!..";
	}
}
