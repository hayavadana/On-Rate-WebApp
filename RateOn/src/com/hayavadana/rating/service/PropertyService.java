package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.bean.PropertyBean;

public interface PropertyService {

	public PropertyBean getProperty(String cat,String name);
	public List<PropertyBean> getPropertyList(String category);
}
