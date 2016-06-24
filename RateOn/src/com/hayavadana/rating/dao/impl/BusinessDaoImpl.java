package com.hayavadana.rating.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.controller.LoginController;
import com.hayavadana.rating.dao.BusinessDao;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Category;

import org.apache.log4j.Logger;

@Repository
public class BusinessDaoImpl implements BusinessDao{
	private static final Logger logger = Logger.getLogger(BusinessDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Business saveBusiness(Business business){

		Session session = null;
		try{
			//session = sessionFactory.openSession();
			session = sessionFactory.openSession();
			
			logger.info("BusinessDaoImpl -- saveBusiness -- Business Name : "+business.getBusinessName());
	        logger.info("BusinessDaoImpl -- saveBusiness -- Account Id : "+business.getAcctId());

			if(null != business.getBusinessId()){
				business.setBusinessName(business.getBusinessName()+" Uppula");
				session.update(business);
			}
			else
			{
				System.out.println("-----BusinessDaoImpl----saveBusiness---newBusiness---");
				System.out.println("----start hours---:"+business.getStartHours());
				System.out.println("----end hours-----:"+business.getEndHours());
				Calendar  calender = new GregorianCalendar();
				String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				business.setCreatedDate(formatter.parse(strCurrentDate));
				business.setIsActive('Y');
	    
	        	session.save(business);	
			}
			
		}catch(Exception ex){
			System.out.println("--------BusinessDaoImpl-----Exception in saving business---ex :");
			ex.printStackTrace();
		}
		finally{
    		session.close();
    	}
		return business;
	}
	public Business updateBusiness(Business business){
		Session session = null;
		try{
			//session = sessionFactory.openSession();
			session = sessionFactory.openSession();
			
			logger.info("BusinessDaoImpl -- updateeBusiness -- Business Name : "+business.getBusinessName());
	        logger.info("BusinessDaoImpl -- updateBusiness -- Account Id : "+business.getAcctId());

		//		business.setBusinessName(business.getBusinessName()+" Uppula");
		//		session.merge(business);
		
//String hql = "UPDATE Business B SET B.businessName = :bName, B.businessDesc = :bDesc,B.categoryCode = :CatCode, B.startDay = :startDay, B.endDay = :endDay , B.startHours = :startHours, B.endHours =endHours WHERE B.businessId = :busiId";
	        StringBuffer hql = 	new StringBuffer("UPDATE Business B SET B.businessName 		= :bName");
	        							hql.append(", B.businessDesc 	= :bDesc");
	        							hql.append(", B.categoryCode 	= :catCode");
	        							hql.append(", B.startDay 		= :startDay");
	        							hql.append(", B.endDay 			= :endDay");
	        							hql.append(", B.startHours 		= :startHours");
	        							hql.append(", B.endHours 		= :endHours");
	        							hql.append(", B.exceptionString 	= :exceptionStr");
	        							hql.append(", B.smsRequired 	= :smsRequired");
	        							hql.append(", B.websiteUrl  	= :websiteUrl");
	        							hql.append(", B.phoneNumber  	= :phoneNumber");
	        							hql.append(" WHERE B.businessId = :busiId");		

			Query query = session.createQuery(hql.toString());
			query.setParameter("bName", business.getBusinessName());
			query.setParameter("bDesc", business.getBusinessDesc());
			query.setParameter("catCode", business.getCategoryCode());
			query.setParameter("startDay", business.getStartDay());
			query.setParameter("endDay", business.getEndDay());
			query.setParameter("startHours", business.getStartHours());
			query.setParameter("endHours", business.getEndHours());
			query.setParameter("exceptionStr", business.getExceptionString());
			
			query.setParameter("smsRequired", business.getSmsRequired());
			query.setParameter("websiteUrl", business.getWebsiteUrl());
			query.setParameter("phoneNumber", business.getPhoneNumber());

			
			query.setParameter("busiId", business.getBusinessId());			
			
			int status = query.executeUpdate();

			/*String hql = "UPDATE Business B SET B.businessName = :bussinessName WHERE B.businessId = :busiId";
			Query query = session.createQuery(hql);
			query.setParameter("bussinessName", business.getBusinessName());
*/			
			
			
		}catch(Exception ex){
			System.out.println("-------------Exception ex :"+ex);
		}
		return business;
	}

	public Business getBusiness(Business busi){
		Business business = null;
		Session session = null;
		Query query = null;
		try{
			String hql = "FROM Business B WHERE B.businessId = :busiId";
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			query.setParameter("busiId", busi.getBusinessId());
			
			List<Business> businessList = query.list();
			for(Business b : businessList)
			{
				business = b;
			}
		}catch(Exception ex){
			System.out.println("-------------Exception ex :"+ex);
		}
		finally{
    		session.close();
    	}
		return business;
	}
	
	public Business getBusiness(Integer businessId, String catCode){
		Business business = null;
		Session session = null;
		Query query = null;
		try{
			String hql = "FROM Business B WHERE B.businessId = :busiId AND B.categoryCode = :catCode";
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			query.setParameter("busiId", businessId);
			query.setParameter("catCode", catCode);
			System.out.println("BusinessDaoImpl -- getBusiness(Integer businessId, String catCode) -- Before list");
			List<Business> businessList = query.list();
			
			System.out.println("BusinessDaoImpl -- getBusiness(Integer businessId, String catCode) -- Before list Sizee : "+businessList.size());
			
			/*for(Business b : businessList)
			{
				business = b;
			}*/
			if(businessList.size() > 0){
				business = businessList.get(0);
				System.out.println("BusinessDaoImpl -- getBusiness(Integer businessId, String catCode) -- Business Id : "+business.getBusinessId() +"  Name : "+business.getBusinessName());
					
			}
			
		}catch(Exception ex){
			System.out.println("-------------Exception ex :"+ex);
		}
		finally{
    		session.close();
    	}
		return business;
		
	}
	public List<Business> getBusinessList(){
		List<Business> businessList = null;
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Business B";
			Query query = session.createQuery(hql);
			
			logger.info("BusinessDaoImpl -- getBusinessList -- before calling database ");
			 
			businessList = query.list();
			logger.info("BusinessDaoImpl -- getBusinessList -- businessList ");

			for(Business buss : businessList){
				logger.info("BusinessDaoImpl -- getBusinessList -- "+buss.getBusinessName()+"       "+buss.getBusinessDesc()+"       "+buss.getCategoryCode());
			}
			
		}catch(Exception ex){
			System.out.println("------BusinessDaoImpl----getBusinessList---- -Exception ex : "+ex);
		}
		finally{
    		session.close();
    	}

		
		return businessList;
	}
	
	public List<Business> getBusinessList(String countryCode,String stateCode,String cityCode,String areaCode,String catCode){
		List<Business> businessList = null;
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
		//	String hql = "SELECT Business FROM Business B,ADDRESS A WHERE B.businessId = A.businessId AND B.categoryCode = :catCode AND A.countryCode = :ctryCode AND A.stateCode = :stateCode AND A.cityCode = :cityCode";
			String hql = "FROM Business B INNER JOIN Address A ON B.businessId = A.businessId WHERE B.categoryCode = :catCode AND A.countryCode = :ctryCode AND A.stateCode = :stateCode AND A.cityCode = :cityCode";
			if(null != areaCode){
				hql = hql + " AND A.areaCode = :areaCode";
			}
			
			Query query = session.createQuery(hql);
			query.setParameter("catCode", catCode);
			query.setParameter("ctryCode", countryCode);
			query.setParameter("stateCode", stateCode);
			query.setParameter("cityCode", cityCode);
			
			if(null != areaCode){
			query.setParameter("areaCode", areaCode);
			}
			
			logger.info("BusinessDaoImpl -- getBusinessList -- before calling database ");
			 
			businessList = query.list();
			logger.info("BusinessDaoImpl -- getBusinessList -- businessList ");

			for(Business buss : businessList){
				logger.info("BusinessDaoImpl -- getBusinessList -- "+buss.getBusinessName()+"       "+buss.getBusinessDesc()+"       "+buss.getCategoryCode());
			}
			
		}catch(Exception ex){
			System.out.println("------BusinessDaoImpl----getBusinessList---- -Exception ex : "+ex);
		}
		finally{
    		session.close();
    	}

		
		return businessList;
		
	}
	public List<Business> getBusinessListByCategory(String catCode){
		List<Business> businessList = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Business B WHERE B.categoryCode = :catCode";
			Query query = session.createQuery(hql);
			query.setParameter("catCode", catCode);
			
			businessList = query.list();
			
			
		}catch(Exception ex){
			System.out.println("-------BusinessDaoImpl------------getBusinessListByCategory -------ex : "+ex);
		}
		finally{
    		session.close();
    	}
		return businessList;
	}
	
	
	public List<Business> getBusinessListOfTrailPeriodExpires(Integer validityDays){
		List<Business> businessList = null;
		Session session = null;
		try{
			Calendar  calender = new GregorianCalendar();
			String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

			calender.add(com.ibm.icu.util.Calendar.DAY_OF_MONTH, -10);
			
			Date toDate = calender.getTime();
			
			session = sessionFactory.openSession();
			
			//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			//Date dtFromDate = formatter.parse(strFromDate);
				
			String hql = "FROM Business B WHERE B.createdDate < :toDate";
			
			Query query = session.createQuery(hql);
			query.setParameter("toDate", toDate);
			//query.setParameter("trialDays", validityDays.doubleValue());
			businessList = query.list();
			System.out.println("-------BusinessDaoImpl------------getBusinessListOfTrailPeriodExpires -------size : "+businessList.size());
			
		}catch(Exception ex){
			System.out.println("-------BusinessDaoImpl------------getBusinessListOfTrailPeriodExpires -------ex : "+ex);
		}
		finally{
    		session.close();
    	}
		return businessList;
	}

	public boolean inactivateBusiness(Integer businessId){
		
		Session session = null;
		boolean inactivated = false;
		try{
			/*Calendar  calender = new GregorianCalendar();
			String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
*/
			
			session = sessionFactory.openSession();
				
			String hql = "UPDATE Business B SET B.isActive = 'N' WHERE B.businessId = :busiId";
			
			Query query = session.createQuery(hql);
			query.setParameter("busiId", businessId);
			
			int count = query.executeUpdate();
			inactivated = true;
			
		}catch(Exception ex){
			System.out.println("-------BusinessDaoImpl------------getBusinessListOfTrailPeriodExpires -------ex : "+ex);
		}
		finally{
    		session.close();
    	}
		return inactivated;
	}
}
