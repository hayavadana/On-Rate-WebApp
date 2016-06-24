package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.bean.BusinessForm;
import com.hayavadana.rating.bean.CouponBean;

public interface CouponService {
	public CouponBean saveCoupon(CouponBean couponBean);
	public List<CouponBean> getAllCoupons(Integer businessId);
	public Integer activateCoupon(Integer couponId);
		
}
