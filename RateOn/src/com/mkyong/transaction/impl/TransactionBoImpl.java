package com.mkyong.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hayavadana.rating.bean.CategoryBean;
import com.hayavadana.rating.service.CategoryService;
import com.mkyong.transaction.TransactionBo;


public class TransactionBoImpl implements TransactionBo {

	/*@Autowired 
	private CategoryService categoryService; 
	*/
	public String save() {
		
		/*System.out.println("TransactionBoImpl -- save");
		List<CategoryBean> catList = categoryService.getCategoriesList();
		for(CategoryBean catBean : catList){
			System.out.println(catBean.getCategoryCode()+"           " +catBean.getCategoryDesc());
		}*/
	/*	
		List<CountryBean> countryList = countryService.getCountriesList();
		for(CountryBean ctryBean : countryList){
			System.out.println(ctryBean.getCountryCode()+"           " +ctryBean.getCountryDesc());
		}
	*/	
		return "Jersey + Spring example";

	}

}