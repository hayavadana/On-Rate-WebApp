package com.hayavadana.rating.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.bean.FileBean;

@Component("fileValidator")
public class FileValidator implements Validator{
	@Override
	public boolean supports(Class<?> type){
		return FileBean.class.isAssignableFrom(type);
	 	
	}
	@Override
	public void validate(Object obj, Errors errors){
		FileBean fileBean = (FileBean)obj;
		
		try{
		
		System.out.println("-------- FileValidator -------Name "+fileBean.getFileName());
		
		System.out.println("-------- FileValidator -------End ");
		}
		catch(Exception ex){
			System.out.println("FileValidator   ---- Exception : "+ex);
		}
	}

}
