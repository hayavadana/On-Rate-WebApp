package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.TopicBean;
import com.hayavadana.rating.dao.TopicDao;
import com.hayavadana.rating.model.Topic;
import com.hayavadana.rating.service.TopicService;
import com.hayavadana.rating.util.TopicUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	private TopicDao topicDao;
	
	@Autowired 
	private TopicUtil topicUtil;
	
	public boolean saveTopic(TopicBean topicBean){
		Topic topic = null;
		boolean result = false;
		
		try{
			
			topic = topicUtil.getTopic(topicBean);
			topic = topicDao.saveTopic(topic);
			if(null != topic.getTopicId()){
				result = true;
			}
		}catch(Exception ex){
			System.out.println("Exception in TopicServiceImpl in saveTopic : "+ex);
		}
		return result;
	}
	
	public List<TopicBean> getAllTopicsByUser(Integer userId){
		List<TopicBean> topicBeanList = null;
		List<Topic> topicList = null;
		
		TopicBean topicBean = null;
		try{
			topicBeanList = new ArrayList<TopicBean>();
			
			topicList = topicDao.getAllTopicsByUser(userId);
			
			for(Topic topic : topicList){
				topicBean = topicUtil.getTopicBean(topic);
				topicBeanList.add(topicBean);
			}
		}catch(Exception ex){
			System.out.println("Exception in TopicServiceImpl in getAllTopicsByUser : "+ex);
		}
		return topicBeanList;
	}
			
			
}
