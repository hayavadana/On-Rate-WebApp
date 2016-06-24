package com.hayavadana.rating.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.UserRateDao;
import com.hayavadana.rating.model.MovieRate;
import com.hayavadana.rating.model.UserRate;
@Repository
public class UserRateDaoImpl implements UserRateDao{
private static final Logger logger = Logger.getLogger(UserRateDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRate saveEndUser(UserRate rate){
		 Session session = null;
			try{
				System.out.println("------in UserRateDaoImpl----saveEndUser---userId:"+rate.getUserId());
				Calendar  calender = new GregorianCalendar();
				String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				rate.setCreatedDate(formatter.parse(strCurrentDate));
				
				
				session = sessionFactory.openSession();
				//session.saveOrUpdate(rate);
				session.save(rate);
				
				System.out.println("---saved/updated userRate-----");
				
				logger.info("UserRateDaoImpl -- Successfully saved UserRate -- UserRate Id : "+rate.getRatingId());
				
			}catch(Exception ex){
				System.out.println("------------Exception : "+ex);
				ex.printStackTrace();
			}
			finally{
	    		session.close();
	    	}
			return rate;
	}

	public Double getAverageUserRating(Integer businessId){
		 Session session = null;
		 Query query = null;
		 Double averageVal = 0d;
			try{
				
				
				session = sessionFactory.openSession();
				String hql = "SELECT Avg(UR.userRate) FROM UserRate UR WHERE UR.businessId = :busiId";
				session = sessionFactory.openSession();
				query = session.createQuery(hql);
				query.setParameter("busiId", businessId);
				
				Iterator<Double> it=query.iterate();
				if(it.hasNext())
					averageVal = it.next();
				System.out.println("UserRateDaoImpl -- getAverageUserRating -- Business Id : "+businessId+"     User Rating : "+averageVal);
				logger.info("UserRateDaoImpl -- getAverageUserRating -- Business Id : "+businessId+"     User Rating : "+averageVal);
				
			}catch(Exception ex){
				System.out.println("------------Exception : "+ex);
			}
			return averageVal;
	}
	public List<UserRate> getUserComments(Integer businessId){
		List<UserRate> userRateList = null;
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM UserRate UR WHERE UR.businessId = :busiId";
			Query query = session.createQuery(hql);
			
			query.setParameter("busiId", businessId);
			
			userRateList = query.list();
			
			logger.info("UserRateDaoImpl -- getUserComments -- UserComments ");

			for(UserRate comments : userRateList){
				logger.info("UserRateDaoImpl -- getUserComments -- Comments : "+comments.getUserComments());
			}
						
		}catch(Exception ex){
			System.out.println("------UserRateDaoImpl----getUserComments---- -Exception ex : "+ex);
		}

		return userRateList;

	}

	@Override
	public List<MovieRate> getMovieUserRateList(Integer movieid) {
		List<MovieRate> rateList=new ArrayList<MovieRate>();
		Session session = null;
		try{
			
			session = sessionFactory.openSession();
			String hql = "FROM MovieRate U WHERE U.movieId = :movieId";
			Query query = session.createQuery(hql);
			query.setParameter("movieId", movieid);
			
			logger.info("UserRateDaoImpl -- getMovieUserRateList -- before calling database ");
			 
			rateList = query.list();
			System.out.println("Total records fetched from DB:"+rateList.size());
			//List<UserRate> rateList=query.list();
			logger.info("UserRateDaoImpl -- getMovieUserRateList -- UserRateList ");

			for(MovieRate uRate : rateList){
				//logger.info("BusinessDaoImpl -- getBusinessList -- "+buss.getBusinessName()+"       "+buss.getBusinessDesc()+"       "+buss.getCategoryCode());
				logger.info(uRate.toString());
			}
			
		}catch(Exception ex){
			System.out.println("------UserRateDaoImpl----getMovieUserRateList---- -Exception ex : "+ex);
			ex.printStackTrace();
		}
		
		return rateList;

	}

	@Override
	public MovieRate saveMovieEndUser(MovieRate movieRate) {
		 Session session = null;
			try{
				Calendar  calender = new GregorianCalendar();
				String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				movieRate.setCreatedDate(formatter.parse(strCurrentDate));
				
				
				session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(movieRate);
				
				logger.info("UserRateDaoImpl -- Successfully saved UserRate -- UserRate Id : "+movieRate.getRatingId());
				
			}catch(Exception ex){
				System.out.println("------------Exception : "+ex);
			}
			return movieRate;
	}

	@Override
	public Float getAverageMovieRating(Integer movieId) {
		 Session session = null;
		 Query query = null;
		 Double averageVal = null ;
			try{
				
				
				session = sessionFactory.getCurrentSession();
				String hql = "SELECT avg(UR.userRate) FROM MovieRate UR WHERE UR.movieId = :movieId";
				session = sessionFactory.openSession();
				query = session.createQuery(hql);
				query.setParameter("movieId", movieId);
				 
				Iterator<Double> it=query.iterate();
				if(it.hasNext())
					averageVal = it.next();
				System.out.println("UserRateDaoImpl -- getAverageUserRating -- Business Id : "+movieId+"     User Rating : "+averageVal);
				logger.info("UserRateDaoImpl -- getAverageUserRating -- Business Id : "+movieId+"     User Rating : "+averageVal);
				
			}catch(Exception ex){
				System.out.println("------------Exception : "+ex);
				ex.printStackTrace();
			}
			if(null!=averageVal){
				return averageVal.floatValue();
			}
			return null;
	}
} 
