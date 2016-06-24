package com.hayavadana.rating.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.hayavadana.rating.bean.CouponBean;

@Component("couponValidator")
public class CouponValidator implements Validator{

	@Override
	public boolean supports(Class<?> type){
		return CouponBean.class.isAssignableFrom(type);
		
	}
	@Override
	public void validate(Object command, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "couponDesc", "field.couponDesc.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "field.startDate.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "field.endDate.empty");
	}
}
