package com.hayavadana.rating.dao.impl;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar ;
import java.util.GregorianCalendar;
import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;

import com.hayavadana.rating.dao.AccountDao;
import com.hayavadana.rating.model.Account;

import org.apache.log4j.Logger;

//@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{
	private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean checkIfEmailIdAlreadyExist(Account account){
		Account existingAccount = null;
		boolean accountExist = false;
		
		List<Account> accountList = null;
		logger.info("AccountDaoImpl -- checkIfEmailIdAlreadyExist -- Before Account Validation");
		logger.info("AccountDaoImpl -- checkIfEmailIdAlreadyExist -- Entered values Email Id : "+account.getEmailId());
	
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Account A WHERE A.emailId = :emailId";
		Query query = session.createQuery(hql);
		query.setParameter("emailId", account.getEmailId());
		
		accountList = query.list();
		
		logger.info("AccountDaoImpl -- checkIfEmailIdAlreadyExist -- List of Account : "+accountList.size());
		
		if(accountList.size() > 0 )
			accountExist = true;
		
		session.close();
		return accountExist;
	}
	public Account checkIfAccountExist(Account account){
	
		Account existingAccount = null;
		boolean accountExist = false;
		
		List<Account> accountList = null;
		logger.info("AccountDaoImpl -- checkIfAccountExist -- Before Account Validation");
		logger.info("AccountDaoImpl -- checkIfAccountExist -- Entered values Email Id : "+account.getEmailId()+"      Password : "+account.getPassword());
		
		
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Account A WHERE A.emailId = :emailId and A.password = :password and A.isActive = 'Y'";
		Query query = session.createQuery(hql);
		query.setParameter("emailId", account.getEmailId());
		query.setParameter("password", account.getPassword());
		
		accountList = query.list();
		
		logger.info("AccountDaoImpl -- checkIfAccountExist -- List of Account : "+accountList.size());
		
		for(Account acctObj : accountList){
		 
			logger.info("AccountDaoImpl -- checkIfAccountExist -- Account details in the database");
			logger.info("AccountDaoImpl -- checkIfAccountExist -- Email Id : "+acctObj.getEmailId()+"         "+acctObj.getPassword());
			
			
			if(acctObj.getEmailId().equals(account.getEmailId()) && acctObj.getPassword().equals(account.getPassword())){
			 accountExist = true;
			 existingAccount = acctObj;
			 logger.info("AccountDaoImpl -- checkIfAccountExist -- Account Found in the database");
			 logger.info("AccountDaoImpl -- checkIfAccountExist -- Entered values Email Id : "+acctObj.getEmailId()+"      Password : "+acctObj.getPassword()+"     isActive : "+acctObj.getIsActive());
			 }
		}
		session.close();
		return existingAccount;
	}
	
	public Account registerAccount(Account account){
		
		Session session=null;
		try{
		Calendar  calender = new GregorianCalendar();
        String strCurrentDate = (calender.get(Calendar.MONTH)+1) +"/"+calender.get(Calendar.DAY_OF_MONTH) +"/" + calender.get(Calendar.YEAR);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        account.setCreatedDate(formatter.parse(strCurrentDate));
        
        session = sessionFactory.openSession();
        
        logger.info("AccountDaoImpl -- registerAccount -- Before Account Validation");
        logger.info("AccountDaoImpl -- registerAccount -- Entered values Email Id : "+account.getEmailId()+"      Password : "+account.getPassword()+"   Phone : "+account.getPhoneNumber());
		
             
		account.setIsActive('Y');
		
		session.save(account);
		
		}catch(Exception ex){
			System.out.println("Exception ---in ---AccountDaoImpl ---registerAccount "+ex);
		}
		finally{
			session.close();
		}
		return account;
	}
	
	public Account getAccountByEmail(String emailId){
		Account acctBean = null;
		Session session = null;
		try{
			List<Account> accountList = null;
			
			boolean accountExist  = false;
			session = sessionFactory.openSession();
			
			String hql ="FROM Account A where A.emailId = :emailId";

			Query query = session.createQuery(hql);
			query.setParameter("emailId", emailId);
			
			accountList = query.list();
			for(Account acctObj : accountList){
				acctBean = acctObj;
				accountExist = true;
			 }
		}catch(Exception ex){
			System.out.println("Exception ---in ---AccountDaoImpl ---getAccountByEmail "+ex);
		}
		finally{
			session.close();
		}
		return acctBean;

	}
	
	public int changePassword(Account account){
		int updateCount = 0;
		Session session = null;
		
		try{
			
			session = sessionFactory.openSession();
			String hql = "UPDATE Account A SET A.password = :pwd WHERE A.acctId = :accountId";

			Query query = session.createQuery(hql);
			query.setParameter("pwd", account.getPassword());
			query.setParameter("accountId", account.getAcctId());
			
			updateCount = query.executeUpdate();

			
		}catch(Exception ex){
			System.out.println("Exception ---in ---AccountDaoImpl ---changePassword "+ex);
		}
		finally{
			session.close();
		}
		
		return updateCount;
	}
	public Account getAccount(Integer acctId){
		Account acctBean = null;
		Session session = null;
		try{
			List<Account> accountList = null;
			
			boolean accountExist  = false;
			session = sessionFactory.openSession();
			
			String hql ="FROM Account A where A.acctId = :acctId";

			Query query = session.createQuery(hql);
			query.setParameter("acctId", acctId);

			accountList = query.list();
			for(Account acctObj : accountList){
				acctBean = acctObj;
				accountExist = true;
			 }
		}catch(Exception ex){
			System.out.println("Exception ---in ---AccountDaoImpl ---getAccount "+ex);
		}
		finally{
			session.close();
		}
		return acctBean;
	}
}
