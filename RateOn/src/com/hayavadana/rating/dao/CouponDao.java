package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Coupon;

public interface CouponDao {

	public Coupon saveCoupon(Coupon coupon);
	public List<Coupon> getCouponsListOfBusiness(Integer businessId);
	public Integer getBusinessIdFromCoupon(Integer couponId);
	public boolean actcivateCoupons(Integer couponId);
	public boolean deactcivateAllCoupons(Integer businessId);
}
