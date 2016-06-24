package com.hayavadana.rating.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.PropertyBean;
import com.hayavadana.rating.dao.PropertyDao;
import com.hayavadana.rating.model.EndUser;
import com.hayavadana.rating.model.Property;
import com.hayavadana.rating.model.UserRate;
import com.hayavadana.rating.service.PropertyService;
import com.hayavadana.rating.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	private PropertyDao propertyDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public PropertyBean getProperty(String cat,String name){
		PropertyBean bean = null;
		Property     prop = null;
		
		try{
			prop = propertyDao.getProperty(cat, name);
			bean = commonUtil.getPropertyBean(prop);
				
		}catch(Exception ex){
			System.out.println("------------StateServiceImpl-------getStateListOfCountry-----------Ex : "+ex);
		}
		
		return bean;
	}
	public List<PropertyBean> getPropertyList(String category){
		/*EndUser user = null;
		UserRate userRate = null;
		boolean rateSaved = false;
*/
		PropertyBean bean = null;
		
		List<Property> propList = null;
		List<PropertyBean> beanList =null;
		
		try{
			propList = propertyDao.getPropertyList(category);
			beanList = new ArrayList<PropertyBean>();
			
			for(Property prop : propList){
				bean = commonUtil.getPropertyBean(prop);
				beanList.add(bean);
			}
			
			}catch(Exception ex){
			System.out.println("------------StateServiceImpl-------getStateListOfCountry-----------Ex : "+ex);
		}
		return beanList;

	}


}
