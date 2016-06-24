package com.hayavadana.rating.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.bean.BusinessForm;

@Component("businessSetupValidator")
public class BusinessSetupValidator implements Validator{

	@Autowired
	private BusinessValidator businessValidator;
	
	@Autowired
	private AddressValidator addressValidator;

	
	public AddressValidator getAddressValidator() {
		return addressValidator;
	}

	public void setAddressValidator(AddressValidator addressValidator) {
		this.addressValidator = addressValidator;
	}

	public BusinessValidator getBusinessValidator() {
		return businessValidator;
	}

	public void setBusinessValidator(BusinessValidator businessValidator) {
		this.businessValidator = businessValidator;
	}
	
	
	@Override
	public boolean supports(Class<?> type){
		return BusinessForm.class.isAssignableFrom(type);
		
	}
	@Override
	public void validate(Object obj, Errors errors){
		BusinessForm busiForm = (BusinessForm)obj;
		
		
		try{
			System.out.println("-------- BusinessSetupValidator -------Start ");

			ValidationUtils.invokeValidator(businessValidator, busiForm.getBusinessBean(), errors);	
			ValidationUtils.invokeValidator(addressValidator, busiForm.getAddressBean(), errors);	
			
			System.out.println("-------- BusinessSetupValidator -------End ");

		}catch(Exception ex)
		{
			System.out.println("-------- BusinessSetupValidator -------Exception : "+ex);

			ex.printStackTrace();
		}
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessBean.businessName", "field.businessName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessBean.businessDesc", "field.businessDesc.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessBean.startHours", "field.startHours.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessBean.endHours", "field.endHours.empty");
		*/
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
