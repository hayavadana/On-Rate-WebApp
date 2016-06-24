package com.hayavadana.rating.webservice.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
//import org.springframework.beans.factory.annotation.Autowired;





import com.hayavadana.rating.bean.AreaBean;
import com.hayavadana.rating.bean.CategoryBean;
import com.hayavadana.rating.bean.CityBean;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.StateBean;
import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.service.CategoryService;
import com.hayavadana.rating.service.CityService;
import com.hayavadana.rating.service.CommonService;
import com.hayavadana.rating.service.CountryService;
import com.hayavadana.rating.service.StateService;
import com.hayavadana.rating.service.impl.BusinessServiceImpl;
import com.hayavadana.rating.service.impl.CountryServiceImpl;

@Component
@Path("/masterServices")
public class CommonRestService {
	private static final Logger logger = Logger.getLogger(BusinessServiceImpl.class);

	@Autowired
    private CountryService countryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;

	@Autowired
	private CommonService commonService;
	
	@GET
	@Path("/countriesList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CountryBean> getAllCountries(){
		
		//countryService = new CountryServiceImpl();
		List<CountryBean> countryList = null;
		CountryBean ctryBean = new CountryBean();
		
		logger.info("CommonRestService --getAllCountries -- before calling Service ");
		
		//System.out.println("-------------CommonRestService-------getAllCountries-----------before calling Service");
		
		countryList = countryService.getCountriesList();
		
		logger.info("CommonRestService --getAllCountries -- after calling Service ");
		//System.out.println("-------------CommonRestService-------getAllCountries-----------after calling Service");

		return countryList;
	}
	
	@GET
	@Path("/categoriesList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryBean> getAllCategories(){
		List<CategoryBean> categoryList = null;
		//System.out.println("-------------CommonRestService-------getAllCountries-----------before calling Service");
		logger.info("CommonRestService --getAllCountries -- before calling Service ");
		
		categoryList = categoryService.getCategoriesList();
		
		logger.info("CommonRestService --getAllCountries -- after calling Service ");
		
		//System.out.println("-------------CommonRestService-------getAllCountries-----------after calling Service");

		return categoryList;
	}
	
	@GET
	@Path("/stateList/country/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StateBean> getStateListOfCountry(@PathParam("country")String ctryCode){
		
		logger.info("CommonRestService --getStateListOfCountry -- Country : "+ctryCode);
		//System.out.println("----------------CommonRestService--------getStateListOfCountry : Country : "+ctryCode);
		
		List<StateBean> stateList =null;
		CountryBean ctryBean = new CountryBean();
		ctryBean.setCountryCode(ctryCode);
		
		logger.info("CommonRestService --getStateListOfCountry -- before calling Service");
		
//System.out.println("-------------CommonRestService-------getStateListOfCountry-----------before calling Service");
		
		stateList = stateService.getStateListOfCountry(ctryBean);
		
		logger.info("CommonRestService --getStateListOfCountry -- after calling Service");
	//	System.out.println("-------------CommonRestService-------getStateListOfCountry-----------after calling Service");

		return stateList;
		
	}
	
	@GET
	@Path("/cityList/country/{country}/state/{state}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CityBean> getCityListofStateAndCountry(@PathParam("state")String stateCode,@PathParam("country")String countryCode){
		logger.info("CommonRestService --getCityListofStateAndCountry -- Country : "+countryCode+"     State : "+stateCode);
//		System.out.println("----------------CommonRestService--------getCityListofStateAndCountry : Country : "+countryCode+"     State : "+stateCode);
		List<CityBean> cityList = null;
		CityBean cityBean = null;
		
		cityList = cityService.getAllCitiesByStateCountry(stateCode, countryCode);
	
		
		return cityList;
	}
	
	@GET
	@Path("/areaList/city/{cityCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AreaBean> getAreaListofCity(@PathParam("cityCode")String cityCode){
		System.out.println("CommonRestService --getAreaListofCity -- City : "+cityCode);
//		System.out.println("----------------CommonRestService--------getCityListofStateAndCountry : Country : "+countryCode+"     State : "+stateCode);
		List<AreaBean> areaList = null;
		AreaBean areaBean = null;
		
		areaList = commonService.getAreaListByCity(Integer.valueOf(cityCode));
	
		
		return areaList;
	}
}
