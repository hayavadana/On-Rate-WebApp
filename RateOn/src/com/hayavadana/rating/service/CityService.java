package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.bean.CityBean;

public interface CityService {
	public List<CityBean> getAllCitiesByStateCountry(String stateCode,String countryCode);
}
