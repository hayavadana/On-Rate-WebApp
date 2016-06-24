package com.hayavadana.rating.dao.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.dao.CountryDao;
import org.apache.log4j.Logger;
//@Repository("countryDao")
public class CountryDaoImpl  implements CountryDao{
	private static final Logger logger = Logger.getLogger(CountryDaoImpl.class);
	@Autowired
	private SessionFactory sesionFactory;
	
	public List<Country> getAllCountries(){
		List<Country> countryList = null;
		Session session = null;
		try{
				
			session = sesionFactory.openSession();
				String hql ="FROM Country C";
				Query query = session.createQuery(hql);
				logger.info("CountryDaoImpl -- getAllCountries --before calling database");
				
				countryList = query.list();
				logger.info("CountryDaoImpl -- getAllCountries -- CountryList" );
				
				for(Country ctry : countryList){
					logger.info("CountryDaoImpl -- getAllCountries -- " + ctry.getCountryCode()+"       "+ctry.getCountryDesc());
					
				}
		}catch(Exception ex){
			System.out.println("------CountryDaoImpl----getAllCountries---- -Exception ex : "+ex);
		}
		finally{
    		session.close();
    	}
		return countryList;
	}
}
