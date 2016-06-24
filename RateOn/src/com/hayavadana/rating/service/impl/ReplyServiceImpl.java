package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.ReplyBean;
import com.hayavadana.rating.dao.ReplyDao;
import com.hayavadana.rating.model.Reply;
import com.hayavadana.rating.service.ReplyService;
import com.hayavadana.rating.util.ForumUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private ForumUtil forumUtil;
	
	public boolean saveReply(ReplyBean replyBean){
		Reply reply = null;
		boolean result = false;
		
		try{
			reply = forumUtil.getReply(replyBean);
			reply = replyDao.saveReply(reply);
			
			if(null != reply.getReplyId())
				result = true;
		}catch(Exception ex){
			System.out.println("Exception in saveTopic of ReplyServiceImpl : "+ex);
		}
		
		return result;
	}
	public List<ReplyBean> getAllRepliesByTopic(Integer topicId){
		
		List<ReplyBean> replyBeanList = null;
		List<Reply> replyList = null;
		ReplyBean replyBean = null;
		
		try{
			replyBeanList = new ArrayList<ReplyBean>();
			replyList = replyDao.getAllRepliesByTopic(topicId);
			
			for(Reply reply : replyList){
				replyBean = forumUtil.getReplyBean(reply);
				replyBeanList.add(replyBean);
			}
			
		}catch(Exception ex){
			System.out.println("Exception in getAllRepliesByTopic of ReplyServiceImpl : "+ex);

		}
		return replyBeanList;
	}
}
