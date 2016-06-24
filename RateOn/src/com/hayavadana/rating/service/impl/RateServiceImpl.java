package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.dao.EndUserDao;
import com.hayavadana.rating.dao.UserRateDao;
import com.hayavadana.rating.model.EndUser;
import com.hayavadana.rating.model.MovieRate;
import com.hayavadana.rating.model.UserRate;
import com.hayavadana.rating.service.RateService;
import com.hayavadana.rating.util.CommonUtil;
import com.hayavadana.rating.util.MailUtil;
import com.hayavadana.rating.webservice.rest.json.RateVO;
import com.hayavadana.rating.webservice.rest.vo.movieRateVO;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class RateServiceImpl implements RateService{
	
	@Autowired
	private EndUserDao endUserDao;
	
	@Autowired
	private UserRateDao userRateDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private MailUtil mailUtil;
	
	
	public boolean saveRate(RateVO rateVO){
		EndUser user = null;
		UserRate userRate = null;
		boolean rateSaved = false;
		try{
			
			user = commonUtil.getEndUser(rateVO);
		//	userRate = commonUtil.getUserRate(rateVO);
			
			user = endUserDao.saveEndUser(user);
			
			userRate = commonUtil.getUserRate(rateVO);
			
			userRate.setUserId(user.getUserId());
			
			userRate.setUserId(user.getUserId());
			userRate = userRateDao.saveEndUser(userRate);
		
			if(rateVO.getEmailId().length()!=0)
				mailUtil.sendCouponMail(rateVO);
				System.out.println("--------coupon mail sent----------");
			rateSaved = true;
		}catch(Exception ex){
			System.out.println("------------StateServiceImpl-------getStateListOfCountry-----------Ex : "+ex);
		}
		return rateSaved;
	}
	
	public List<RateVO> getUserComments(Integer businessId){
		//UserRate userRate = null;
		RateVO rateVO = null;
		boolean rateSaved = false;
		List<RateVO> rateList  = null;
		List<UserRate> userRateList = null;	
		userRateList = userRateDao.getUserComments(businessId);
		rateList = new ArrayList<RateVO>();
		
		for(UserRate userRate : userRateList){
			//userRate = commonUtil.getUserRate(rateVO);
			rateVO = commonUtil.getRateVO(userRate);
			
			if(null==rateVO.getUserComments()){
				rateVO.setUserComments("--comments not available--");
			}
			else if(rateVO.getUserComments().length()==0){
				rateVO.setUserComments("--comments not available--");
			}
			rateList.add(rateVO);
		}
		
		return rateList;
	}

	@Override
	public List<movieRateVO> getMovieUserRateList(movieRateVO rateVO) {
		List<movieRateVO> rateList=new ArrayList<movieRateVO>();
		List<MovieRate> rList=new ArrayList<MovieRate>();
		movieRateVO voTemp=null;
		System.out.println("in getUserRateList method of service method just before calling dao");
		rList=userRateDao.getMovieUserRateList(rateVO.getMovieId());
		System.out.println("after calling dao in service method");
		if(rList!=null){
			for(MovieRate test:rList)
			{
			voTemp=commonUtil.getMovieRateVO(test);
			System.out.println("RateVO :"+voTemp.toString());
			if(null==voTemp.getUserComments()){
				voTemp.setUserComments("--comments not available--");
			}
			else if(voTemp.getUserComments().length()==0){
				voTemp.setUserComments("--comments not available--");
			}
			rateList.add(voTemp);
			}
		}
		return rateList;
	}

	@Override
	public void saveMovieRate(movieRateVO rateVO) {
		EndUser user = null;
		MovieRate movieRate = null;
		boolean rateSaved = false;
		try{
			
			user = commonUtil.getMovieEndUser(rateVO);
			movieRate = commonUtil.getMovieRate(rateVO);
			
			user = endUserDao.saveEndUser(user);
			
			movieRate.setUserId(user.getUserId());
			movieRate = userRateDao.saveMovieEndUser(movieRate);
		/*
			if(rateVO.getEmailId().length()!=0)
				mailUtil.sendCouponMail(rateVO);
			*/
			rateSaved = true;
		}catch(Exception ex){
			System.out.println("------------StateServiceImpl-------getStateListOfCountry-----------Ex : "+ex);
		}
	//	return rateSaved;
	}
}
