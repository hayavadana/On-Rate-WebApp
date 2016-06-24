package com.hayavadana.rating.util;

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.webservice.rest.vo.BusinessVO;

public class BusinessUtil {
	
	public BusinessForm getBusinessForm(Business business, Address address){
		BusinessForm form = new BusinessForm();
		form = setBusinessToForm(form,business);
		form = setAddressToForm(form,address);
		
		return form;
		
	}
public BusinessForm setBusinessToForm(BusinessForm form,Business business){
	
	//BusinessBean bussBean = new BusinessBean();
	/*form.setBusinessId(business.getBusinessId());
	form.setBusinessName(business.getBusinessName());
	form.setBusinessDesc(business.getBusinessDesc());
	form.setCategoryCode(business.getCategoryCode());
	form.setStartDay(business.getStartDay());
	form.setEndDay(business.getEndDay());
	form.setStartHours(business.getStartHours());
	form.setEndHours(business.getEndHours());
	form.setExceptionStr(business.getExceptionString());
	form.setSmsRequired(business.getSmsRequired());
	form.setWebsiteUrl(business.getWebsiteUrl());
	form.setAcctId(business.getAcctId());
	form.setIsActive(business.getIsActive()+"");
	*/
	return form;
}
public BusinessForm setAddressToForm(BusinessForm form,Address address){
	//Address address = new Address();
	
	/*form.setAddressId(address.getAddressId());
	form.setLineOne(address.getLineOne());
	form.setLineTwo(address.getLineTwo());
	form.setCity(address.getCityCode());
	form.setState(address.getStateCode());
	form.setCountry(address.getCountryCode());
	form.setLandmark(address.getLandmark());
	form.setPostalCode(address.getPostalCode());
	form.setBusinessId(address.getBusinessId());
	*/
	return form;
}

public Business getBusinessFromForm(BusinessForm form){
	Business bussModel = new Business();
//	bussModel.setBusinessId()

	/*bussModel.setBusinessId(form.getBusinessId());
	bussModel.setBusinessName(form.getBusinessName());
	bussModel.setBusinessDesc(form.getBusinessDesc());
	bussModel.setCategoryCode(form.getCategoryCode());
	bussModel.setStartDay(form.getStartDay());
	bussModel.setEndDay(form.getEndDay());
	bussModel.setStartHours(form.getStartHours());
	bussModel.setExceptionString(form.getExceptionStr());
	bussModel.setSmsRequired(form.getSmsRequired());
	bussModel.setWebsiteUrl(form.getWebsiteUrl());
	bussModel.setAcctId(form.getAcctId());
	*/
	return bussModel;
}

public Address getAddressFromForm(BusinessForm form){
	Address address = new Address();
	
	/*address.setAddressId(form.getAddressId());
	address.setLineOne(form.getLineOne());
	address.setLineTwo(form.getLineTwo());
	address.setCityCode(form.getCity());
	address.setStateCode(form.getState());
	address.setCountryCode(form.getCountry());
	address.setLandmark(form.getLandmark());
	address.setPostalCode(form.getPostalCode());
	address.setBusinessId(form.getBusinessId());
*/	
	return address;
}
public BusinessVO getVOFromBean(BusinessBean busiBean,AddressBean addressBean){
	
	BusinessVO businessVO = new BusinessVO();
	
	businessVO.setBusinessId(busiBean.getBusinessId());
	businessVO.setBusinessName(busiBean.getBusinessName());
	businessVO.setBusinessDesc(busiBean.getBusinessDesc());
	if(null != addressBean){
	businessVO.setBusinessAddress(addressBean.getLineOne()+"  "+addressBean.getLineTwo()+"  "+addressBean.getLandmark()+"   "+addressBean.getCity()+"   "+addressBean.getState()+"   "+addressBean.getCountry()+"   "+addressBean.getPostalCode());
		
	}
	return businessVO;

}
public BusinessVO getBusinessVO(Business business,Address address,Logo logo){
	BusinessVO businessVO = new BusinessVO();
	
	businessVO.setBusinessId(business.getBusinessId());
	businessVO.setBusinessName(business.getBusinessName());
/*	String view = "<h2 style='color: #0080FF;'><U>KFC BAR & Restaurant</U></h2>"
			+ "<address><h4>Tokyo is the capital of Japan, the center of the Greater Tokyo Area, and the most populous metropolitan area in the world.</h4>"
			+ "<b>Visit us at:</b><br>Grand New Plaza <br>Box 564, Disneyland<br>USA<br>Postal Code: 5000072 <br>Phone Number: <a href='tel:1-847-555-5555'>1-847-555-5555</a> <br>"
			+ "<a href='www.google.com'>www.google.com</a><br></address><br><address><b>Working Hours:</b><br>Monday to Friday<br>8:00 AM to 9:00 PM<br>Saturday 8:00 AM to 12:00 PM<br>"
			+ "</address>";
*/
	
//	businessVO.setBusinessDesc(view);
	if(null != address){
	businessVO.setBusinessAddress(address.getLineOne()+"  "+address.getLineTwo()+"  "+address.getLandmark()+"   "+address.getCityCode()+"   "+address.getStateCode()+"   "+address.getCountryCode()+"   "+address.getPostalCode());		
	businessVO.setLongitude(address.getLongitude());
	businessVO.setLatitude(address.getLatitude());
	}
	String landmark="",url="",maps="",phoneNumber="";
	if(null!=address.getLandmark()){
		landmark=address.getLandmark();
	}
	if(null!=business.getWebsiteUrl()){
		if(business.getWebsiteUrl().contains("http://")){
			url=business.getWebsiteUrl();
		}
		else{
			url="http://"+business.getWebsiteUrl();
		}
	}
	if(null!=address.getLatitude() && null!=address.getLongitude()){
		maps="<a href='http://maps.google.com/maps?q="+address.getLatitude()+","+address.getLongitude()+"'>Find in Maps</a>";
	}
	if(null!=business.getPhoneNumber())
	{
		 String Number=business.getPhoneNumber();
		 phoneNumber=" <br>Phone Number: <a href='tel:"+Number+"'>"+Number+"</a>";
	}
	String view = "<h2 style='color: #0080FF;'><U>"+business.getBusinessName()+"</U></h2><address>"
			+ "<h4>"+business.getBusinessDesc()+"</h4>"
			+ "<b>Visit us at:</b><br>"+address.getLineOne()+"<br>"+address.getLineTwo()+","+landmark+"<br>"+address.getStateCode()+","+address.getCountryCode()+",Postal Code: "+address.getPostalCode()+""+phoneNumber
			+ "<br><a href='"+url+"'>"+business.getWebsiteUrl()+"</a><br>"+maps+"</address><br><address><b>Working Hours:</b><br>"+business.getStartDay()+" to "+business.getEndDay()+"<br>"+business.getStartHours()+" to "+business.getEndHours()
			+ "</address>";
	
	businessVO.setBusinessDesc(view);
	
	if(null != logo){
		businessVO.setBusinessLogo(logo.getLogoData());
	}
	return businessVO;
}

}
