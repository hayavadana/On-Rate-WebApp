package com.hayavadana.rating.service;

import java.util.List;
import java.util.Map;


import com.hayavadana.rating.bean.CategoryBean;

public interface CategoryService {
	public List<CategoryBean> getCategoriesList();
	public Map<String,String> getCategoriesMap();
	public CategoryBean getCategoyBeanByCode(String catCode);
}