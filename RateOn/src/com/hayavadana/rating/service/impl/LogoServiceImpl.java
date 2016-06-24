package com.hayavadana.rating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hayavadana.rating.bean.LogoBean;
import com.hayavadana.rating.dao.CommonDao;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.service.LogoService;
import com.hayavadana.rating.util.CommonUtil;

public class LogoServiceImpl implements LogoService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private CommonUtil commonUtil;

	
	public Logo saveLogo(Logo logo) {
		// TODO Auto-generated method stub
		logo=commonDao.saveLogoDetails(logo);
		return null;
	}

	
	public LogoBean getLogoBean(Integer businessId) {
		// TODO Auto-generated method stub
		Logo logo=null;
		LogoBean logoBean=null;
		
		logo=commonDao.getLogoDetails(businessId);
		
		if(null!=logo){
			System.out.println("logo is retrived in service method");
			logoBean=commonUtil.getLogoBean(logo);
		}
		else System.out.println("logo is null");
		return logoBean;
	}

}
