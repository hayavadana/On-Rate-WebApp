package com.hayavadana.rating.util;

import com.hayavadana.rating.bean.LikesBean;
import com.hayavadana.rating.bean.ReplyBean;
import com.hayavadana.rating.bean.SubscriberBean;
import com.hayavadana.rating.bean.TopicBean;
import com.hayavadana.rating.model.Likes;
import com.hayavadana.rating.model.Reply;
import com.hayavadana.rating.model.Subscriber;
import com.hayavadana.rating.model.Topic;

public class ForumUtil {
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
	
	public Reply getReply(ReplyBean replyBean){
		Reply reply = new Reply();
		
		reply.setReplyId(replyBean.getReplyId());
		reply.setReplyDescription(replyBean.getReplyDescription());
		reply.setTopicid(replyBean.getTopicId());
		reply.setUserId(replyBean.getUserId());
		
		return reply;
	}
	
	public ReplyBean getReplyBean(Reply reply){
		ReplyBean bean = new ReplyBean();
		bean.setReplyId(reply.getReplyId());
		bean.setReplyDescription(reply.getReplyDescription());
		bean.setTopicId(reply.getTopicid());
		bean.setUserId(reply.getUserId());
		
		return bean;
	}
	
	public Likes getLikes(LikesBean bean){
		Likes likes = new Likes();
		
		likes.setLikesId(bean.getLikesId());
		likes.setReplyId(bean.getReplyId());
		likes.setTopicId(bean.getTopicId());
		likes.setUserId(bean.getUserId());
		
		return likes;
	}
	
	public LikesBean getLikesBean(Likes likes){
		LikesBean bean = new LikesBean();
		
		bean.setLikesId(likes.getLikesId());
		bean.setReplyId(likes.getReplyId());
		bean.setTopicId(likes.getTopicId());
		bean.setUserId(likes.getUserId());
		
		return bean;
	}
	
	public Subscriber getSubscriber(SubscriberBean bean){
		Subscriber sub = new Subscriber();
		
		sub.setSubscriberId(bean.getSubscriberId());
		sub.setTopicId(bean.getTopicId());
		sub.setUserId(bean.getUserId());
		
		return sub;
	}
	
	public SubscriberBean getSubscriberBean(Subscriber sub){
		SubscriberBean bean = new SubscriberBean();
		
		bean.setSubscriberId(sub.getSubscriberId());
		bean.setTopicId(sub.getTopicId());
		bean.setUserId(sub.getUserId());
		
		return bean;
	}
}
