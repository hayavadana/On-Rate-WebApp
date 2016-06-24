package com.hayavadana.rating.service.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class TestScheduler {

	@Scheduled(cron="*/5 * * * * *")
	public void run(){
		try{
			
			String dateParam = new Date().toString();
			//System.out.println(" Date : "+dateParam);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
}
