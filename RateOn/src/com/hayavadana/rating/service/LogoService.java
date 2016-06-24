package com.hayavadana.rating.service;

import com.hayavadana.rating.bean.LogoBean;
import com.hayavadana.rating.model.Logo;

public interface LogoService {
	public Logo saveLogo(Logo logo);
	public LogoBean getLogoBean(Integer businessId);				//bean
}
