package com.hayavadana.rating.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.LikesDao;
import com.hayavadana.rating.model.Likes;

@Repository
public class LikesDaoImpl implements LikesDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Likes saveLikes(Likes likes){
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(likes);
		}catch(Exception ex){
			System.out.println("Exception in saveLikes of LikesDaoImpl : "+ex);
		}
		finally{
    		session.close();
    	}
		return likes;
	}
}
