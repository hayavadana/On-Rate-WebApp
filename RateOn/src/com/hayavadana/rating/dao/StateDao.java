package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Country;
import com.hayavadana.rating.model.State;

public interface StateDao {
	public List<State> getStateListByCountry(Country country);
}
