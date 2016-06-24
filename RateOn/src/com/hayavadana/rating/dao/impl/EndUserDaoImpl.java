package com.hayavadana.rating.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.EndUserDao;
import com.hayavadana.rating.model.EndUser;

@Repository
public class EndUserDaoImpl implements EndUserDao{
	private static final Logger logger = Logger.getLogger(EndUserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 public EndUser saveEndUser(EndUser user){
		 Session session = null;
			try{
				System.out.println("------in EndUserDaoImpl----saveEndUser---");
				Calendar  calender = new GregorianCalendar();
				String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				user.setCreatedDate(formatter.parse(strCurrentDate));
				
				session = sessionFactory.openSession();
				
				session.save(user);
				
				System.out.println("---saved/updated endUser-----");
				System.out.println("EndUserDaoImpl -- Successfully saved EndUser -- EndUser Id : "+user.getUserId());
				logger.info("EndUserDaoImpl -- Successfully saved EndUser -- EndUser Id : "+user.getUserId());
				
			}catch(Exception ex){
				System.out.println("----EndUserDaoImpl-------Exception : "+ex);
			}
			finally{
	    		session.close();
	    	}
			return user;
 	  }
 }
