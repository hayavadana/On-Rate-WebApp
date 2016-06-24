package com.hayavadana.rating.service;

import java.util.List;
import java.util.Map;

import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.bean.CountryBean;

public interface CountryService {
	public List<CountryBean> getCountriesList();
	public Map<String,String> getCountriesMap();
}
