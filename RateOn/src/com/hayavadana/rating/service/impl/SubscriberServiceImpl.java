package com.hayavadana.rating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.SubscriberBean;
import com.hayavadana.rating.dao.SubscriberDao;
import com.hayavadana.rating.model.Likes;
import com.hayavadana.rating.model.Subscriber;
import com.hayavadana.rating.service.SubscriberService;
import com.hayavadana.rating.util.ForumUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class SubscriberServiceImpl implements SubscriberService{
	@Autowired
	private ForumUtil forumUtil;
	
	@Autowired
	private SubscriberDao subscriberDao;
	
	public boolean saveSubscriber(SubscriberBean subBean){
		boolean result = false;
	
		Subscriber sub = null;
		try{
			sub = forumUtil.getSubscriber(subBean);
			//likes = likesDao.saveLikes(likes);
			sub = subscriberDao.saveSubscriber(sub);
			
			if(null != sub.getSubscriberId())
				result = true;
		}catch(Exception ex){
			System.out.println("Exception in saveSubscriber of SubscriberServiceImpl : "+ex);
		}
		
		return result;

	}
}
