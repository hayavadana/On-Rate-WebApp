package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;

public interface BusinessService {
	public BusinessForm saveBusiness(BusinessForm busiForm);
	public List<BusinessBean> getBusinessListOfAccount(AccountBean account);
	public BusinessBean getBusinessDetails(BusinessBean bean);
	public List<BusinessBean> getBusinessListByCategory(String catCode);

	public List<BusinessBean> getBusinessListOfTrailPeriodExpires();
	public boolean inactivateBusiness(Integer businessId);
	
	public List<BusinessVO> getBusinessVOList(String countryCode,String StateCode,String CityCode,String areaCode,String CatCode);
	public BusinessVO getBusinessVODetails(BusinessBean bean);
	
}
