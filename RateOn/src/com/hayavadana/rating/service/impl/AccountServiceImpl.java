package com.hayavadana.rating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.model.Account;
import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.controller.LoginController;
import com.hayavadana.rating.dao.AccountDao;
import com.hayavadana.rating.service.AccountService;
import com.hayavadana.rating.util.AccountUtil;
import com.hayavadana.rating.util.CommonUtil;
import com.hayavadana.rating.util.MailUtil;

import org.apache.log4j.Logger;

//@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AccountServiceImpl implements AccountService{
	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AccountUtil accountUtil;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@Autowired 
	private MailUtil mailUtil;
	
	public AccountBean checkIfAccountExist(AccountBean acctBean){
		boolean acctExist = false;
		AccountBean accountBean = null;
		
		logger.info("AccountServiceImpl -- checkIfAccountExist");
		Account account = accountUtil.getAccountModel(acctBean);
		account = accountDao.checkIfAccountExist(account);
		if(null != account)
			accountBean = accountUtil.getAccountBean(account);
		
		return accountBean;
	}
	
	public boolean     checkIfEmailIdAlreadyExist(AccountBean acctBean){
		boolean acctExist = false;
		AccountBean accountBean = null;
		
		logger.info("AccountServiceImpl -- checkIfEmailIdAlreadyExist");
		Account account = accountUtil.getAccountModel(acctBean);
		acctExist = accountDao.checkIfEmailIdAlreadyExist(account);
		
		return acctExist;
	}
	public AccountBean registerAccount(AccountBean acctBean){
		boolean status =false;
	//	AccountBean newBean = null;
		logger.info("AccountServiceImpl -- registerAccount");
		Account account = accountUtil.getAccountModel(acctBean);
		boolean emailIdExist = checkIfEmailIdAlreadyExist(acctBean);
		if(emailIdExist){
			acctBean = null;
		}
		else
		{
			account=  accountDao.registerAccount(account);
			acctBean.setAccountId(account.getAcctId());
			status = mailUtil.sendWelcomeMail(acctBean);
		}
		//accountUtil.sendMail(acctBean);
		return acctBean;
	}

	public AccountBean getAccountByEmail(String emailId){
		
		boolean status =false;
		AccountBean bean = null;
		logger.info("AccountServiceImpl -- getAccountByEmail");
		
		Account account =  accountDao.getAccountByEmail(emailId);
		if(null != account ){
			bean = accountUtil.getAccountBean(account);
			status = mailUtil.forgotPasswordMail(bean);
			//status = accountUtil.sendMail(bean);		
		}
			
		return bean;
	}
	
public AccountBean getAccount(Integer acctId){
		
		boolean status =false;
		AccountBean bean = null;
		logger.info("AccountServiceImpl -- getAccount");
		
		Account account =  accountDao.getAccount(acctId);
		if(null != account ){
			bean = accountUtil.getAccountBean(account);	
		}
			
		return bean;
	}
	

	public int changePassword(AccountBean acctBean){
		
		Account account = null;
		account = accountUtil.getAccountModel(acctBean);
		int count = accountDao.changePassword(account);
		
		return count;
	}
}
