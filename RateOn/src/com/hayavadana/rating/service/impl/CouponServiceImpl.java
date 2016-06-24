package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.CouponBean;
import com.hayavadana.rating.dao.CouponDao;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Coupon;
import com.hayavadana.rating.service.CouponService;
import com.hayavadana.rating.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CouponServiceImpl implements CouponService{
	private static final Logger logger = Logger.getLogger(CouponServiceImpl.class);

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private CouponDao couponDao;
	
	public CouponBean saveCoupon(CouponBean couponBean){
		
		Coupon coupon = new Coupon();
		CouponBean bean = new CouponBean();
		
		coupon = commonUtil.getCoupon(couponBean);
		boolean status = couponDao.deactcivateAllCoupons(couponBean.getBusinessId());
		coupon = couponDao.saveCoupon(coupon);
		bean = commonUtil.getCouponBean(coupon);
		
		return bean;
	}
	public List<CouponBean> getAllCoupons(Integer businessId){
		
		List<CouponBean> beanList = new ArrayList<CouponBean>();
		CouponBean bean = null;
		//Coupon coupon = null;;		
		List<Coupon> couponList = couponDao.getCouponsListOfBusiness(businessId);
		
		for(Coupon coupon : couponList){
			bean = commonUtil.getCouponBean(coupon);	    
			beanList.add(bean);
			logger.info("CouponServiceImpl -- getAllCoupons -- CouponId : "+bean.getBusinessId()+"  -- Desc -- "+bean.getCouponDesc()+" -- IsActive -- "+bean.getIsActive());
			
		}
		
		return beanList;		
	}
	
	public Integer activateCoupon(Integer couponId){
		
		Integer businessId = couponDao.getBusinessIdFromCoupon(couponId);
	
		boolean deactivated = couponDao.deactcivateAllCoupons(businessId);
		if(deactivated)
			couponDao.actcivateCoupons(couponId);		
		return businessId;
	}

}
