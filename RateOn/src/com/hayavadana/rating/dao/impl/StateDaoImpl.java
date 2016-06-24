package com.hayavadana.rating.dao.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.StateDao;
import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.model.State;

@Repository
public class StateDaoImpl implements StateDao{
	private static final Logger logger = Logger.getLogger(StateDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<State> getStateListByCountry(Country country){
		
		List<State> stateList = null;
		Session session = null;
		
		try{
			
			session = sessionFactory.openSession();
			String hql = "FROM State S WHERE S.countryCode = :ctryCd";
			
			Query query = session.createQuery(hql);
			query.setParameter("ctryCd", country.getCountryCode());
			
			logger.info("StateDaoImpl -- getStateListByCountry---- -before calling database ");
			
			stateList = query.list();

			logger.info("StateDaoImpl -- getStateListByCountry -- StateList ");
			for(State state : stateList){
				logger.info(state.getStateCode()+"       "+state.getStateDesc()+"        "+state.getCountryCode());
			}
			
		}catch(Exception ex){
			System.out.println("------StateDaoImpl-------getStateListByCountry --Ex :" + ex);
		}
		finally{
    		session.close();
    	}
		return stateList;
	}
}
