package com.hayavadana.rating.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.bean.MailBean;
import com.hayavadana.rating.webservice.rest.json.RateVO;

public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendCouponMail(RateVO rate){
		MailBean mailBean = new MailBean();
		
		mailBean.setMailTo(rate.getEmailId());
		mailBean.setMailSub("Welcome to BRM...Coupon");
		mailBean.setMailMessage("Thanks for the Rate on the Business....Please find the attached Coupon.");
		mailBean.setMailFrom("no-reply@hayavadana.com");
		
		sendMail(mailBean);
		return true;
        
	}
	
	public boolean forgotPasswordMail(AccountBean acctBean){
		MailBean mailBean = new MailBean();
		
		mailBean.setMailTo(acctBean.getEmailId());
		mailBean.setMailSub("Forgot Password of BRM App..");
		mailBean.setMailMessage("Your password is : "+acctBean.getPassword());
		mailBean.setMailFrom("no-reply@hayavadana.com");
		
		sendMail(mailBean);
		
		return true;
	}
	
	public boolean sendWelcomeMail(AccountBean acctBean){
		MailBean mailBean = new MailBean();
		
		mailBean.setMailTo(acctBean.getEmailId());
		mailBean.setMailSub("Welcome to BRM..");
		mailBean.setMailMessage("You are successfully registered with BRM...");
		mailBean.setMailFrom("no-reply@hayavadana.com");
		
		sendMail(mailBean);
		return true;
        
	}
	
	public boolean sendMailForTrialExpires(AccountBean acctBean,String businessName){
		MailBean mailBean = new MailBean();
		
		mailBean.setMailTo(acctBean.getEmailId());
		mailBean.setMailSub("Trial Version expired...");
		mailBean.setMailMessage("Dear. "+acctBean.getAccountName()+" Your Business "+businessName+" has been deactivated. Please contact Admin to Acivate...");
		mailBean.setMailFrom("no-reply@hayavadana.com");
		
		sendMail(mailBean);
		return true;
        
	}
	
	 private boolean sendMail(MailBean mailBean){
		 
		 	boolean mailSent = false;
	        try{
	        	SimpleMailMessage emailMsg = new SimpleMailMessage();
	        	emailMsg.setTo(mailBean.getMailTo());
	        	emailMsg.setFrom(mailBean.getMailFrom());
	        	emailMsg.setSubject(mailBean.getMailSub());
	        	emailMsg.setText(mailBean.getMailMessage());
	       
	        	mailSender.send(emailMsg);
	        	mailSent = true;
	        }catch(Exception ex){
	        	System.out.println("Exception in Mail Sent : "+ex);
	        }
			return true;
	}
	
}
