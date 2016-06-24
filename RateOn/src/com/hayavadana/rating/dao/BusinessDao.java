package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;

public interface BusinessDao {
	public Business saveBusiness(Business business);
	public Business updateBusiness(Business business);
	public Business getBusiness(Business business);
	public Business getBusiness(Integer businessId, String catCode);
	public List<Business> getBusinessList();
	public List<Business> getBusinessListByCategory(String catCode);
	
	public List<Business> getBusinessListOfTrailPeriodExpires(Integer validityDays);
	public boolean inactivateBusiness(Integer businessId);
	
	public List<Business> getBusinessList(String countryCode,String stateCode,String cityCode,String areaCode,String catCode);	
	
}
