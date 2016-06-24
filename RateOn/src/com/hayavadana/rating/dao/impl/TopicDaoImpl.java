package com.hayavadana.rating.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.TopicDao;
import com.hayavadana.rating.model.Topic;

@Repository
public class TopicDaoImpl implements TopicDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Topic saveTopic(Topic topic){
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(topic);
			
		}catch(Exception ex){
			System.out.println("Exception in TopicDaoImpl --saveTopic : "+ex);
		}
		finally{
    		session.close();
    	}
		return topic;
	}
	
	public java.util.List<Topic> getAllTopicsByUser(Integer userId){
		java.util.List<Topic> topicList = null;
		Session session = null;
		Query query = null;
		String hql = null;
		try{
			session = sessionFactory.openSession();
			
			if(null != userId){
				hql = "FROM Topic T WHERE T.userId = :userId";
				query = session.createQuery(hql);
				query.setParameter("userId", userId);
			}
			else{
				hql = "FROM Topic T";
				query = session.createQuery(hql);
			}
			topicList = query.list();
			
		}catch(Exception ex){
			System.out.println("Exception in TopicDaoImpl --getAllTopicsByUser : "+ex);
		}
		finally{
    		session.close();
    	}
		return topicList;
	}

}
