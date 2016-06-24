package com.hayavadana.rating.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.MailBean;
import com.hayavadana.rating.model.Account;

//@Component("accountUtil")
public class AccountUtil {
	
	
	
	public Account getAccountModel(AccountBean acctBean){
		
		Account account = new Account();
		account.setEmailId(acctBean.getEmailId());
		account.setPassword(acctBean.getPassword());
		account.setAcctName(acctBean.getAccountName());
		account.setPhoneNumber(acctBean.getPhoneNumber());
		account.setAcctId(acctBean.getAccountId());
		account.setCountryCode(acctBean.getCountryCode());
		account.setIsActive('Y');
		
		return account;
	}
	public AccountBean getAccountBean(Account acct){
		AccountBean bean = new AccountBean();
		bean.setEmailId(acct.getEmailId());
		bean.setPassword(acct.getPassword());
		bean.setAccountName(acct.getAcctName());
		bean.setPhoneNumber(acct.getPhoneNumber());
		bean.setAccountId(acct.getAcctId());
		bean.setCountryCode(acct.getCountryCode());
		bean.setIsActive(acct.getIsActive());
		
		return bean;
	}
	
	
	public boolean sendMail(AccountBean acctBean){
		
		
		String recipientAddress  = acctBean.getEmailId();
        String subject = "Test mail from Spring API";
        String message ="This is the first mail program written in Spring API";
       
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
       
  //      mailSender.send(email);
   
		return true;
	}
}
