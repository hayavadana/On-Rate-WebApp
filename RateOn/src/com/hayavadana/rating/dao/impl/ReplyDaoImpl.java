package com.hayavadana.rating.dao.impl;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.ReplyDao;
import com.hayavadana.rating.model.Reply;

@Repository
public class ReplyDaoImpl implements ReplyDao{
	@Autowired
	private SessionFactory sessionFactory;
		
	public Reply saveReply(Reply reply){
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(reply);
		}catch(Exception ex){
			System.out.println("Exception in saveReply of ReplyDaoImpl : "+ex);
		}
		finally{
    		session.close();
    	}
		return reply;
	}
	
	public List<Reply> getAllRepliesByTopic(Integer topicId){
		List<Reply> replyList = null;
		Session session = null;
		Query query = null;
		String hql = "FROM Reply R WHERE R.topicId = :topicId";
		try{
			session = sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			query.setParameter("topicId", topicId);
			replyList = query.list();
			
		}catch(Exception ex){
			System.out.println("Exception in getAllRepliesByTopic of ReplyDaoImpl"+ex);
		}
		finally{
    		session.close();
    	}
		return replyList;
	}
}
