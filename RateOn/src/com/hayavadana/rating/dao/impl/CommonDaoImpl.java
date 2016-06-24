package com.hayavadana.rating.dao.impl;



import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;






import com.hayavadana.rating.dao.CommonDao;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Area;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.model.Logo;
import com.hayavadana.rating.model.Movie;

@Repository
public class CommonDaoImpl implements CommonDao{
	private static final Logger logger = Logger.getLogger(CouponDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CommonDao commonDao;
	
	public Logo saveLogoDetails(Logo logo){
		Session session = null;
		
		System.out.println("------------------In CommonDaoImpl----------------");
		System.out.println("Logo BusinessId : "+ logo.getBusinessId());
		System.out.println("Logo File Name : "+ logo.getLogoName());
		System.out.println("Logo Path Name : "+ logo.getLogoPath());
		
		
		try{
			System.out.println("-------------Before Saving Logo----------------");
			
			System.out.println("logo businessId:"+logo.getBusinessId());
			Logo logoId = commonDao.getLogoDetails(logo.getBusinessId());
			session = sessionFactory.openSession();
			
			if(null!=logoId){
				System.out.println("in if block to update logo");
				logo.setLogoId(logoId.getLogoId());
			}
			else{
				System.out.println("in else block to save logo");
		//		session.save(logo);
			}
			
			Transaction tx=session.beginTransaction();
			System.out.println("before save/update logo");
			session.saveOrUpdate(logo);
			tx.commit();
			System.out.println("after save/update logo");
			
			System.out.println("-------------After Saving Logo----------------");
			
		}catch(Exception ex){
			System.out.println("Exception --- CommonDaoImpl --saveLogoDetails : "+ex);
			ex.printStackTrace();
		}
		finally{
    		session.close();
    	}
		return logo;
		
	}
	
	public Logo getLogoDetails(Integer businessId){
		Session session = null;
		Logo logo = null;
		if(null!=businessId){
		try{
			System.out.println("CommonDaoImpl -- getLogoDetails(Integer businessId)  --  For Business Id : "+businessId);
			session = sessionFactory.openSession();
			
			String hql = "FROM Logo L WHERE L.businessId = :bussId";
    	
			Query query = session.createQuery(hql);
    		query.setParameter("bussId",businessId);
    		System.out.println("CommonDaoImpl -- getLogoDetails(Integer businessId)  --  Before Query List For Business Id : "+businessId);
			
    		List<Logo> logoList = query.list();
    		System.out.println("CommonDaoImpl -- getLogoDetails(Integer businessId)  --  After Query List -- Size : " + logoList.size());
			
    		if(logoList.size() > 0){
    			logo = logoList.get(0);
    			System.out.println("CommonDaoImpl -- getLogoDetails(Integer businessId)  --  Logo : "+logo.getLogoName());		
    		}
			System.out.println("-------------After Getting Logo----------------");
			
		}catch(Exception ex){
			System.out.println("Exception --- CommonDaoImpl --getLogoDetails : "+ex);
		}
		finally{
			session.close();
		}
		}
		return logo;
		
	}
	
	public List<Area> getAreaListByCity(Integer cityCode){
		Session session = null;

		List<Area> areaList = null;
	
		try{
			
			session = sessionFactory.openSession();
			String hql = "FROM Area A WHERE A.cityCode = :cityCd";
			System.out.println("CommonDaoImpl  -- getAreaListByCity -- Selected City : "+cityCode);
			Query query = session.createQuery(hql);
			query.setParameter("cityCd", cityCode);
			
			areaList = query.list();
			System.out.println("CommonDaoImpl  -- getAreaListByCity -- AreaList : "+areaList.size());
			for(Area areaObj : areaList)
				System.out.println("AreaCode : "+areaObj.getAreaCode()+"    Desc : "+areaObj.getAreaDesc()+"    CityCode : "+areaObj.getCityCode());
			
		}catch(Exception ex){
			System.out.println("Exception ---in ---CommonDaoImpl ---getAreaListByCity "+ex);
		}
		finally{
			session.close();
		}
		return areaList;
	}
	
	
	//------------------------------Movie----------------------------------//

	@Override
	public List<Movie> getMovieListByLanguage(String language) {
		Session session = null;
		
		List<Movie> movieList = null;
	
		try{
			
			session = sessionFactory.getCurrentSession();
			String hql = "FROM Movie A WHERE A.language = :lang";
			System.out.println("CommonDaoImpl  -- getMovieListByLanguage -- Selected City : "+language);
			Query query = session.createQuery(hql);
			query.setParameter("lang", language);
			
			movieList = query.list();
			System.out.println("CommonDaoImpl  -- getMovieListByLanguage -- AreaList : "+movieList.size());
			for(Movie movieObj : movieList)
				System.out.println("MovieId : "+movieObj.getMovieId()+"    MovieName : "+movieObj.getMovieName()+"    language : "+movieObj.getLanguage());
			
		}catch(Exception ex){
			System.out.println("Exception ---in ---CommonDaoImpl ---getMovieListByLanguage "+ex);
		}
		return movieList;
	}

	@Override
	public Movie getMovieDetails(Movie movie) {
		Session session = null;
		List<Movie> movieList = null;
		Movie movieDetails=new Movie();
		try{
			
			session = sessionFactory.getCurrentSession();
			String hql = "FROM Movie A WHERE A.movieId = :movieId";
			System.out.println("CommonDaoImpl  -- getMovieListByLanguage -- Selected City : "+movie.getMovieId());
			Query query = session.createQuery(hql);
			query.setParameter("movieId", movie.getMovieId());
			
			movieList = query.list();
			System.out.println("CommonDaoImpl  -- getMovieListByLanguage -- AreaList : "+movieList.size());
			for(Movie movieObj : movieList){
				movieDetails = movieObj;
				System.out.println("MovieId : "+movieObj.getMovieId()+"    MovieName : "+movieObj.getMovieName()+"    language : "+movieObj.getLanguage());
			}
		}catch(Exception ex){
			System.out.println("Exception ---in ---CommonDaoImpl ---getMovieListByLanguage "+ex);
		}
		return movieDetails;
	}

	@Override
	public void saveMovie(Movie movie) {
		System.out.println(" ---------CommonDaoImpl ---saveMovie----------");
		Session session = null;
		
		try{
			session=sessionFactory.openSession();
			session.saveOrUpdate(movie);
			System.out.println("-------CommonDaoImpl ---saveMovie-------movie saved successfully---");
		}
		catch(Exception ex){
			System.out.println("--------Exception----in----CommonDaoImpl ---saveMovie----------"+ex);
			ex.printStackTrace();
		}
		finally{
			session.close();
		}
		
	}
}
