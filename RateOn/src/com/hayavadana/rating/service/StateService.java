package com.hayavadana.rating.service;

import java.util.List;
import java.util.Map;

import com.hayavadana.rating.bean.CountryBean;
import com.hayavadana.rating.bean.StateBean;

public interface StateService {
	public List<StateBean> getStateListOfCountry(CountryBean ctryBean);
	public Map<String,String> getStatesMapOfCountry(CountryBean ctryBean);
}
