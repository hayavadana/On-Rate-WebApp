package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.City;

public interface CityDao {
	public List<City> getAllCitiesByStateCountry(String stateCode,String ctryCode);
}
