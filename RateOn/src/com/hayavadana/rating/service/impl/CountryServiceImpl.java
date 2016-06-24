package com.hayavadana.rating.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.service.CountryService;
import com.hayavadana.rating.controller.LoginController;
import com.hayavadana.rating.dao.CountryDao;
import com.hayavadana.rating.dao.impl.CountryDaoImpl;
import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.util.CommonUtil;
import org.apache.log4j.Logger;

//@Service("countryService")
//@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CountryServiceImpl implements CountryService{
	private static final Logger logger = Logger.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryDao countryDao;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	public List<CountryBean> getCountriesList(){
		List<Country> countryList = null;
		List<CountryBean> ctryList = null;
		CountryBean ctryBean = null;
		try{
			//countryDao = new CountryDaoImpl();
			//commonUtil = new CommonUtil();
			logger.info("CountryServiceImpl -- getCountriesList --before calling DAO");
			
			//System.out.println("-------------CountryServiceImpl-------getCountriesList-----------before calling DAO");
			countryList = countryDao.getAllCountries();
			ctryList = new ArrayList<CountryBean>();
			logger.info("CountryServiceImpl -- getCountriesList --After calling DAO");
			//System.out.println("-------------CountryServiceImpl-------getCountriesList-----------After calling DAO");
			for(Country country : countryList)
			{
				ctryBean = commonUtil.getCountryBean(country);
				ctryList.add(ctryBean);
			}
			logger.info("CountryServiceImpl -- getCountriesList --before returning");
			//System.out.println("-------------CountryServiceImpl-------getCountriesList-----------before returning...");
		}catch(Exception ex){
			System.out.println("CountryServiceImpl -- getCountriesList -- Exception : "+ex);
		}
		
		return ctryList;
	}
	
	public Map<String,String> getCountriesMap(){
		List<Country> countryList = null;
		Map<String,String> ctryMap = null;
		CountryBean ctryBean = null;
		try{
			//countryDao = new CountryDaoImpl();
			//commonUtil = new CommonUtil();
			logger.info("CountryServiceImpl -- getCountriesList --before calling DAO");
			
			//System.out.println("-------------CountryServiceImpl-------getCountriesList-----------before calling DAO");
			countryList = countryDao.getAllCountries();
			ctryMap = new HashMap<String,String>();
			logger.info("CountryServiceImpl -- getCountriesList --After calling DAO");
			//System.out.println("-------------CountryServiceImpl-------getCountriesList-----------After calling DAO");
			for(Country country : countryList)
			{
				ctryBean = commonUtil.getCountryBean(country);
				ctryMap.put(ctryBean.getCountryCode(),ctryBean.getCountryDesc());
			}
			logger.info("CountryServiceImpl -- getCountriesList --before returning");
			//System.out.println("-------------CountryServiceImpl-------getCountriesList-----------before returning...");
		}catch(Exception ex){
			System.out.println("CountryServiceImpl -- getCountriesList -- Exception : "+ex);
		}
		
		return ctryMap;
	}
	
}
