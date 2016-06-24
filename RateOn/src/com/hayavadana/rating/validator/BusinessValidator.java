package com.hayavadana.rating.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hayavadana.rating.bean.BusinessBean;


@Component("businessValidator")
public class BusinessValidator implements Validator{
	@Override
	public boolean supports(Class<?> type){
		return BusinessBean.class.isAssignableFrom(type);
	 	//return type == BusinessBean.class;
		
	}
	@Override
	public void validate(Object obj, Errors errors){
		BusinessBean businessBean = (BusinessBean)obj;
		
		try{
		
		System.out.println("-------- BusinessValidator -------Name "+businessBean.getBusinessName());
		
		if(null != businessBean.getBusinessName() && !businessBean.getBusinessName().trim().equals("")){
			System.out.println("-------- BusinessValidator -------IF");	
		}
		else
		{
			errors.reject("businessName", "businessName should not be empty");
		}
		if(null != businessBean.getBusinessDesc() && !businessBean.getBusinessDesc().trim().equals("")){
			System.out.println("-------- BusinessValidator -------IF");	
		}
		else
		{
			errors.reject("businessDesc", "businessDesc should not be empty");
		}
		if(null != businessBean.getCategoryCode() && !businessBean.getCategoryCode().trim().equals("")){
			System.out.println("-------- BusinessValidator -------IF");	
		}
		else
		{
				errors.reject("categoryCode", "categoryCode should not be empty");
		}
		if(null != businessBean.getStartDay() && !businessBean.getStartDay().trim().equals("")){
			System.out.println("-------- BusinessValidator -------IF");	
		}
		else
		{
			errors.reject("startDay", "StartDay should not be empty");
		}
		if(null != businessBean.getEndDay() && !businessBean.getEndDay().trim().equals("")){
			System.out.println("-------- BusinessValidator -------IF");	
		}
		else
		{
			errors.reject("endDay", "endDay should not be empty");
		}
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessName", "field.businessName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessDesc", "field.businessDesc.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startHours", "field.startHours.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endHours", "field.endHours.empty");
		*/
		System.out.println("-------- BusinessValidator -------End ");
		}
		catch(Exception ex){
			System.out.println("BusinessValidator   ---- Exception : "+ex);
		}
		/*if(!acctBean.getPassword().isEmpty() && !acctBean.getReenterPassword().isEmpty()){
			if(acctBean.getPassword().equals(acctBean.getReenterPassword())){
				
			}else
			{
				//System.out.println("0000000000000-----both should be match00000000000000000");
				errors.reject("Password", "Password and Re-Enter Password should match");
			}
		}*/
	}
}
