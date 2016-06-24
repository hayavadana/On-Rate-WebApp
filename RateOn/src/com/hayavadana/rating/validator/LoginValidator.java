package com.hayavadana.rating.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import com.hayavadana.rating.bean.LoginBean;

@Component("loginValidator")
public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> type){
		return LoginBean.class.isAssignableFrom(type);
		
	}
	@Override
	public void validate(Object command, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "field.emailId.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.password.empty");
	}
}
