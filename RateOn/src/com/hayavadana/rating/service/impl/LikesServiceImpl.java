package com.hayavadana.rating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.LikesBean;
import com.hayavadana.rating.dao.LikesDao;
import com.hayavadana.rating.model.Likes;
import com.hayavadana.rating.service.LikesService;
import com.hayavadana.rating.util.ForumUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class LikesServiceImpl implements LikesService{
	@Autowired
	private ForumUtil forumUtil;
	
	@Autowired
	private LikesDao likesDao;
	
	public boolean saveLikes(LikesBean likesBean){
		boolean result = false;
		Likes likes = null;
		try{
			likes = forumUtil.getLikes(likesBean);
			likes = likesDao.saveLikes(likes);
			
			if(null != likes.getLikesId())
				result = true;
		}catch(Exception ex){
			System.out.println("Exception in saveLikes of LikesServiceImpl : "+ex);
		}
		
		return result;
	}

}
