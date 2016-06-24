package com.hayavadana.rating.service.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.hayavadana.rating.bean.BusinessBean;
import com.hayavadana.rating.service.BusinessService;

public class TrialPeriodExpirySchedular {

	@Autowired
	private BusinessService businessService;
	
	@Scheduled(cron="0 0 10 * * *")
	public void run(){
		try{
			
			String dateParam = new Date().toString();
			List<BusinessBean> busiList = businessService.getBusinessListOfTrailPeriodExpires();
			System.out.println(" --- Business Trial Usage Expiry Validation --- ");
			for(BusinessBean bean : busiList){
				System.out.println(" Expirying Business : "+bean.getBusinessName());
				//businessService
			}
			//inactivate the business
			//sending mail 
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
