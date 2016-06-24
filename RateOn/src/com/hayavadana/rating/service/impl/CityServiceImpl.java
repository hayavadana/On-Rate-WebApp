package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.CategoryBean;
import com.hayavadana.rating.bean.CityBean;
import com.hayavadana.rating.dao.CityDao;
import com.hayavadana.rating.model.Category;
import com.hayavadana.rating.model.City;
import com.hayavadana.rating.service.CityService;
import com.hayavadana.rating.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public List<CityBean> getAllCitiesByStateCountry(String stateCode,String ctryCode){
		List<CityBean> cityBeanList = null;
		List<City> cityList = null;
		
		CityBean cityBean = null;
		try{
			cityList = cityDao.getAllCitiesByStateCountry(stateCode, ctryCode);
			cityBeanList = new ArrayList<CityBean>();
			
			for(City city : cityList){
				cityBean = commonUtil.getCityBean(city);
				cityBeanList.add(cityBean);				
			}
		}catch(Exception ex){
			System.out.println("CityServiceImpl -- getAllCitiesByStateCountry -- Exception : "+ex);
		}
				return cityBeanList;
	}
}
