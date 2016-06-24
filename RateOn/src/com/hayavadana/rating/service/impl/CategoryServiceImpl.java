package com.hayavadana.rating.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.CategoryBean;
import com.hayavadana.rating.dao.CategoryDao;
import com.hayavadana.rating.model.Category;
import com.hayavadana.rating.service.CategoryService;
import com.hayavadana.rating.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)

public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public List<CategoryBean> getCategoriesList(){
		
		List<Category> categoryList = null;
		List<CategoryBean> catList = null;
		
		CategoryBean catBean = null;
		try{
			categoryList = categoryDao.getAllCategories();
			catList = new ArrayList<CategoryBean>();
			
			for(Category category : categoryList){
				catBean = commonUtil.getCategoryBean(category);
				catList.add(catBean);
			}
			
		}catch(Exception ex){
			System.out.println("CategoryServiceImpl -- getCategoriesList -- Exception : "+ex);
		}
		
		return catList;		
	}
public Map<String,String> getCategoriesMap(){
		
		List<Category> categoryList = null;
		Map<String,String> catMap = null;
		
		CategoryBean catBean = null;
		try{
			categoryList = categoryDao.getAllCategories();
			catMap = new HashMap<String,String>();
			
			for(Category category : categoryList){
				catBean = commonUtil.getCategoryBean(category);
				catMap.put(catBean.getCategoryCode(),catBean.getCategoryDesc());
			}
			
		}catch(Exception ex){
			System.out.println("CategoryServiceImpl -- getCategoriesList -- Exception : "+ex);
		}
		
		return catMap;		
	}
public CategoryBean getCategoyBeanByCode(String catCode){
	CategoryBean bean = null;
	Category cat = null;
	
	cat = categoryDao.getCategory(catCode);
	bean = commonUtil.getCategoryBean(cat);
	
	return bean;
}
}
