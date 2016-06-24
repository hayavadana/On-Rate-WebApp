package com.hayavadana.rating.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.SubscriberDao;
import com.hayavadana.rating.model.Subscriber;

@Repository
public class SubscriberDaoImpl implements SubscriberDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Subscriber saveSubscriber(Subscriber sub){
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(sub);
		}catch(Exception ex){
			System.out.println("Exception in saveSubscriber of SubscriberDaoImpl : "+ex);
		}
		finally{
    		session.close();
    	}
		return sub;
	}
}
