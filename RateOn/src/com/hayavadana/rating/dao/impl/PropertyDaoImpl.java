package com.hayavadana.rating.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;

import com.hayavadana.rating.dao.PropertyDao;
import com.hayavadana.rating.model.Property;

@Repository
public class PropertyDaoImpl implements PropertyDao{
	
	private static final Logger logger = Logger.getLogger(PropertyDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Property getProperty(String cat,String name){
		Session session = null;
		List<Property> propList = null;
		Property prop  = null;
		
		try{
			session = sessionFactory.openSession();
			
			String hql = "FROM Property P WHERE P.category = :cat AND P.name = :name AND P.activeFlag = 'Y'";
			Query query = session.createQuery(hql);
			
			query.setParameter("cat", cat);
			query.setParameter("name", name);
			
			propList = query.list();
			
			if(propList.size()> 0)
				prop = propList.get(0);
		}catch(Exception ex){
			System.out.println("PropertyDaoImpl -- getPropertyList -- Exception : "+ex);			
		}
		finally{
    		session.close();
    	}
		return prop;
	
	}
	public List<Property> getPropertyList(String category){
		Session session = null;
		List<Property> propList = null;
		try{
			session = sessionFactory.openSession();
			
			String hql = "FROM Property P WHERE P.category = :cat AND P.activeFlag = 'Y'";
			Query query = session.createQuery(hql);
			//query.setParameter("activeFlag", "Y");
			query.setParameter("cat", category);
			 
			propList = query.list();
			
		}catch(Exception ex){
			System.out.println("PropertyDaoImpl -- getPropertyList -- Exception : "+ex);			
		}
		finally{
    		session.close();
    	}
		return propList;
	
	}

}
