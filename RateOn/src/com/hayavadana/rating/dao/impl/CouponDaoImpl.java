package com.hayavadana.rating.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;

import com.hayavadana.rating.dao.CouponDao;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Coupon;

import org.apache.log4j.Logger;

@Repository
public class CouponDaoImpl implements CouponDao{
	private static final Logger logger = Logger.getLogger(CouponDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Coupon saveCoupon(Coupon coupon){
		
		Session session = null;
		
		Calendar  calender = new GregorianCalendar();
		String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		
		try{
			session = sessionFactory.openSession();
			
			coupon.setCreatedDate(formatter.parse(strCurrentDate));
			//coupon.setEndDate(formatter.parse(strCurrentDate));
			//coupon.setCreatedDate(formatter.parse(strCurrentDate));
			coupon.setIsActive('Y');
				
			logger.info("CouponDaoImpl -- saveCoupon -- CouponDesc : "+coupon.getCouponDesc());
			logger.info("CouponDaoImpl -- saveCoupon -- StartDate : "+coupon.getStartDate());
			logger.info("CouponDaoImpl -- saveCoupon -- Enddate : "+coupon.getEndDate());
			logger.info("CouponDaoImpl -- saveCoupon -- CreatedDate : "+coupon.getCreatedDate());
			
			session.save(coupon);
			
		}catch(Exception ex){
			
			System.out.println("CouponDaoImpl -- saveCoupon -- Exception : "+ex);
		}
		finally{
    		session.close();
    	}
		return coupon;
	}
	
	public List<Coupon> getCouponsListOfBusiness(Integer businessId){
		
		Session session = null;
		List<Coupon> couponList = null;
		
		Calendar  calender = new GregorianCalendar();
		String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		
		try{
			
			session = sessionFactory.openSession();
			
			String hql = "FROM Coupon C WHERE C.businessId = :busiId";
			Query query = session.createQuery(hql);
			query.setParameter("busiId", businessId);
			
			couponList = query.list();
			
			/*for(Coupon coupon : couponList)
			{
				System.out.println("--CouponDao ---- CouponId : "+coupon.getCouponId()+" -- Coupon : "+coupon.getCouponDesc()+" -- IsActive : "+coupon.getIsActive());
			}*/
			logger.info("CouponDaoImpl -- getCouponsListOfBusiness -- list : "+couponList.size());
			 
		}catch(Exception ex){
			System.out.println("CouponDaoImpl -- getCouponsListOfBusiness -- Exception : "+ex);
		}
		finally{
    		session.close();
    	}
		return couponList;
	}
	
	public Integer getBusinessIdFromCoupon(Integer couponId){
		
		Session session = null;
		Coupon coupon = null;
		
		try{
			session = sessionFactory.openSession();
			
			String hql = "FROM Coupon C WHERE C.couponId = :cId";
			Query query = session.createQuery(hql);
			query.setParameter("cId", couponId);
			
			List<Coupon> couponList = query.list();
			if(couponList.size() > 0)
				coupon = couponList.get(0);
			
		}catch(Exception ex){
			System.out.println("CouponDaoImpl -- getBusinessIdFromCoupon -- Exception : "+ex);			
		}
		finally{
    		session.close();
    	}
		return coupon.getBusinessId();
	}
	
	public boolean deactcivateAllCoupons(Integer businessId){
		Session session = null;
		boolean deactivated = false;
		
		try{
			session = sessionFactory.openSession();
			
			String hql = "UPDATE Coupon C SET C.isActive = :activeFlag WHERE C.businessId = :busiId";
			Query query = session.createQuery(hql);
			query.setParameter("activeFlag", "N");
			query.setParameter("busiId", businessId);
			
			int status = query.executeUpdate();
			
		//	System.out.println("CouponDaoImpl -- deactcivateCoupons -- Status : "+status);		
			logger.info("CouponDaoImpl -- deactcivateCoupons -- Status : "+status);
			
			if(status > 0) deactivated = true;
		}catch(Exception ex){
			System.out.println("CouponDaoImpl -- deactcivateCoupons -- Exception : "+ex);			
		}
		finally{
    		session.close();
    	}
		return deactivated;
	}
	
	public boolean actcivateCoupons(Integer couponId){
		Session session = null;
		boolean activated = false;
		
		try{
			session = sessionFactory.openSession();
			
			String hql = "UPDATE Coupon C SET C.isActive = :activeFlag WHERE C.couponId = :couponId";
			Query query = session.createQuery(hql);
			query.setParameter("activeFlag", "Y");
			query.setParameter("couponId", couponId);
			
			int status = query.executeUpdate();
			if(status == 1) activated = true;
		}catch(Exception ex){
			System.out.println("CouponDaoImpl -- deactcivateCoupons -- Exception : "+ex);			
		}
		finally{
    		session.close();
    	}
		return activated;
	}
}
