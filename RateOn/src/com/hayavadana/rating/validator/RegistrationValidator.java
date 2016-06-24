package com.hayavadana.rating.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hayavadana.rating.bean.AccountBean;

@Component("registrationValidator")
public class RegistrationValidator implements Validator{

	@Override
	public boolean supports(Class<?> type){
		return AccountBean.class.isAssignableFrom(type);
		
	}
	@Override
	public void validate(Object obj, Errors errors){
		AccountBean acctBean = (AccountBean)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "field.emailId.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.password.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reenterPassword", "field.reenterPassword.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", "field.accountName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "field.phoneNumber.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "field.countryCode.empty");
		
		if(!acctBean.getPassword().isEmpty() && !acctBean.getReenterPassword().isEmpty()){
			if(acctBean.getPassword().equals(acctBean.getReenterPassword())){
				
			}else
			{
				//System.out.println("0000000000000-----both should be match00000000000000000");
				errors.reject("Password", "Password and Re-Enter Password should match");
			}
		}
		if(!acctBean.getAccountName().isEmpty())
		{
			if(acctBean.getAccountName().length() < 10 || acctBean.getAccountName().length() > 40 )
				errors.rejectValue("accountName", "field.accountName.range");
		}
		if(!acctBean.getEmailId().isEmpty())
		{
			if(acctBean.getEmailId().length()< 10 ||acctBean.getEmailId().length() > 40 )
				errors.rejectValue("emailId", "field.emailId.range");
		}

		if(!acctBean.getPassword().isEmpty() )
		{
			if(acctBean.getPassword().length() < 8 || acctBean.getPassword().length() > 25)
				errors.rejectValue("password", "field.password.range");
		}
	}
}

