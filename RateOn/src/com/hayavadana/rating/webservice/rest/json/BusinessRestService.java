package com.hayavadana.rating.webservice.rest.json;

import java.util.ArrayList;
import java.util.List;

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

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.CityBean;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.service.AddressService;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.impl.AccountServiceImpl;
import com.hayavadana.rating.util.BusinessUtil;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;

@Component
@Path("/businessService")
public class BusinessRestService {
	private static final Logger logger = Logger.getLogger(BusinessRestService.class);

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private AddressService addressService;
	
	@GET
	@Path("businessDetails/business/{business_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public BusinessVO getBusinessDetails(@PathParam("business_id")String businessId){
		System.out.println("----you are in getBusinessDetails-------" );
		BusinessBean busiBean = new BusinessBean();
		BusinessVO busiVO = new BusinessVO();
		busiBean.setBusinessId(Integer.valueOf(businessId));
		/*busiBean = businessService.getBusinessDetails(busiBean);
	    AddressBean addressBean = addressService.getAddress(busiBean.getBusinessId());
	    
	    BusinessUtil businessUtil = new BusinessUtil();
	    
	    busiVO = businessUtil.getVOFromBean(busiBean,addressBean);
	    */
		busiVO =  businessService.getBusinessVODetails(busiBean);
		
		return busiVO;
	}
	
	@POST
	@Path("rating/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserRating(BusinessBean businessBean){
		System.out.println("Input data from Client : "+businessBean.toString());
		
		return Response.status(200).entity(businessBean).build();
	}

	@GET
	@Path("/businessList/category/{category}/country/{country}/state/{state}/city/{city}/area/{area}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusinessVO> getBusinessListsByCountryStateCityAndCategory(@PathParam("category")String catCode,@PathParam("country")String countryCode,@PathParam("state")String stateCode,@PathParam("city")String cityCode,@PathParam("area")String areaCode){
		logger.info("BusinessRestService --getBusinessListsByCountryStateCityAndCategory -- Country : "+countryCode+"     State : "+stateCode+"     City : "+cityCode+"     Category : "+catCode);

		System.out.println("BusinessRestService --getBusinessListsByCountryStateCityAndCategory -- Country : "+countryCode+"     State : "+stateCode+"     City : "+cityCode+"     Category : "+catCode+ " AreaCode : "+areaCode);

		List<BusinessVO> getBusinessVOList =  businessService.getBusinessVOList(countryCode, stateCode, cityCode,areaCode, catCode);
				
		return getBusinessVOList;
	}
	@GET
	@Path("/businessList/category/{category}/country/{country}/state/{state}/city/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusinessVO> getBusinessListsByCountryStateCityAndCategory(@PathParam("category")String catCode,@PathParam("country")String countryCode,@PathParam("state")String stateCode,@PathParam("city")String cityCode){
		logger.info("BusinessRestService --getBusinessListsByCountryStateCityAndCategory -- Country : "+countryCode+"     State : "+stateCode+"     City : "+cityCode+"     Category : "+catCode);

		List<BusinessVO> getBusinessVOList =  businessService.getBusinessVOList(countryCode, stateCode, cityCode,null,catCode);
				
		return getBusinessVOList;
	}
}
