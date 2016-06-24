package com.hayavadana.rating.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.CategoryDao;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Category;

import org.apache.log4j.Logger;
@Repository
public class CategoryDaoImpl implements CategoryDao{
	private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> getAllCategories(){
		List<Category> categoryList = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Category C";
			Query query = session.createQuery(hql);
			
			logger.info("CategoryDaoImpl -- getAllCategories -- before calling database");

			//System.out.println("------CategoryDaoImpl----getAllCategories---- -before calling database ");
			
			categoryList = query.list();

			logger.info("CategoryDaoImpl -- getAllCategories -- CategoryList");

			//System.out.println("------CategoryDaoImpl----getAllCategories-----CategoryList");
			for(Category cat : categoryList){

				logger.info("CategoryDaoImpl -- getAllCategories -- "+cat.getCategoryCode()+"       "+cat.getCategoryDesc());
				//System.out.println(cat.getCategoryCode()+"       "+cat.getCategoryDesc());
			}
			
		}catch(Exception ex){
			System.out.println("------CategoryDaoImpl----getAllCategories---- -Exception ex : "+ex);
		}
		finally{
    		session.close();
    	}
		return categoryList;
	}
	public Category getCategory(String catCode){
		Category category = null;
		Session session = null;
		List<Category> catList = null;
		Query query = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Category C WHERE C.categoryCode = :catCode";
			query = session.createQuery(hql);
			query.setParameter("catCode",catCode);
		
			catList = query.list();
			if(catList.size()>0)
				category = catList.get(0);
		
		}catch(Exception ex){
			System.out.println("------CategoryDaoImpl----getCategory---- -Exception ex : "+ex);
		}
		finally{
    		session.close();
    	}
		return category;
	}
}
