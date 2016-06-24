package com.hayavadana.rating.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hayavadana.rating.dao.CityDao;
import com.hayavadana.rating.model.Category;
import com.hayavadana.rating.model.City;

public class CityDaoImpl implements CityDao {
	private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<City> getAllCitiesByStateCountry(String stateCode,String ctryCode){
		List<City> cityList = null;
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			String hql = "FROM City C WHERE C.stateCode = :stateCode AND C.countryCode = :ctryCode";
			Query query = session.createQuery(hql);
			query.setParameter("stateCode", stateCode);
			query.setParameter("ctryCode", ctryCode);
			cityList = query.list();

			logger.info("CityDaoImpl -- getAllCitiesByStateCountry -- CityList -- CountryCode : "+ctryCode+"    StateCode : "+stateCode);
			System.out.println("CityDaoImpl -- getAllCitiesByStateCountry -- CityList -- CountryCode : "+ctryCode+"    StateCode : "+stateCode);

			//System.out.println("------CategoryDaoImpl----getAllCategories-----CategoryList");
			for(City city : cityList){

				logger.info("CityDaoImpl -- getAllCitiesByStateCountry -- "+city.getCityCode()+"       "+city.getCityDesc());
				System.out.println("CityDaoImpl -- getAllCitiesByStateCountry -- "+city.getCityCode()+"       "+city.getCityDesc());
				//System.out.println(cat.getCategoryCode()+"       "+cat.getCategoryDesc());
			}

			
		}catch(Exception ex){
			System.out.println("------CityDaoImpl----getAllCitiesByStateCountry---- -Exception ex : "+ex);
		}
		finally{
    		session.close();
    	}
		
		return cityList;
		
	}
	
	/*
	 public List<Category> getAllCategories(){
		List<Category> categoryList = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Category C";
			Query query = session.createQuery(hql);
			
			logger.info("CategoryDaoImpl -- getAllCategories -- before calling database");

			//System.out.println("------CategoryDaoImpl----getAllCategories---- -before calling database ");
			
			categoryList = query.list();

			logger.info("CategoryDaoImpl -- getAllCategories -- CategoryList");

			//System.out.println("------CategoryDaoImpl----getAllCategories-----CategoryList");
			for(Category cat : categoryList){

				logger.info("CategoryDaoImpl -- getAllCategories -- "+cat.getCategoryCode()+"       "+cat.getCategoryDesc());
				//System.out.println(cat.getCategoryCode()+"       "+cat.getCategoryDesc());
			}
			
		}catch(Exception ex){
			System.out.println("------CategoryDaoImpl----getAllCategories---- -Exception ex : "+ex);
		}
		return categoryList;
	}
	 */
}
