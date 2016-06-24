package com.hayavadana.rating.util;

import com.hayavadana.rating.bean.TopicBean;
import com.hayavadana.rating.model.Topic;

public class TopicUtil {

	public Topic getTopic(TopicBean topicBean){
		Topic topic = new Topic();
		
		topic.setTopicCategoryId(topicBean.getDomain());
		topic.setTopicDescription(topicBean.getDescription());
		topic.setTopicTypeCode(topicBean.getTopicType());
		topic.setUserId(topicBean.getUserId());
	
		return topic;
		
	}
	
	public TopicBean getTopicBean(Topic topic){
		TopicBean bean  = new TopicBean();
		bean.setDescription(topic.getTopicDescription());
		bean.setDomain(topic.getTopicCategoryId());
		bean.setTopicType(topic.getTopicTypeCode());
		bean.setUserId(topic.getUserId());
		return bean;
	}
}
