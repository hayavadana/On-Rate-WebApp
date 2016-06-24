package com.hayavadana.rating.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hayavadana.rating.bean.AddressBean;


@Component("addressValidator")
public class AddressValidator implements Validator{

	@Override
	public boolean supports(Class<?> type){
		return AddressBean.class.isAssignableFrom(type);
	 	
	}
	@Override
	public void validate(Object obj, Errors errors){
		AddressBean addressBean = (AddressBean)obj;
		
		try{
		
			if(null != addressBean.getState() && !addressBean.getState().trim().equals("")){
				System.out.println("-------- AddressValidator -------IF State");
				
			}
			else
			{
				errors.reject("state", "Address State should not be empty");
			}
			if(null != addressBean.getCity() && !addressBean.getCity().trim().equals("")){
				System.out.println("-------- AddressValidator -------IF City");
				
			}
			else
			{
				errors.reject("city", "Address City should not be empty");
			}
		
		System.out.println("-------- AddressValidator -------End ");
		}
		catch(Exception ex){
			System.out.println("AddressValidator   ---- Exception : "+ex);
		}
	}

}
