package com.hayavadana.rating.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.AddressDao;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Business;

@Repository

public class AddressDaoImpl implements AddressDao{
	private static final Logger logger = Logger.getLogger(AddressDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Address saveAddress(Address addr){
		Session session = null;
		try{
			//System.out.println("AddressDaoImpl  -- saveAddress ");
			//session = sessionFactory.openSession();
			session = sessionFactory.openSession();
			session.saveOrUpdate(addr);
			//session.flush();
			logger.info("AddressDaoImpl -- Successfully saved Address -- Address Id : "+addr.getAddressId());
			
		}catch(Exception ex){
			System.out.println("------------Exception -- AddressDaoImpl : "+ex);
		}
		finally{
			session.close();
		}
		return addr;
	}
	public Address updateAddress(Address address){
		Session session = null;
		try{
			//System.out.println("AddressDaoImpl  -- saveAddress ");
			//session = sessionFactory.openSession();
			session = sessionFactory.openSession();
			
			logger.info("AddressDaoImpl --  updating Address -- Address Id : "+address.getAddressId());
			
			StringBuffer hql = 	new StringBuffer("UPDATE Address A SET A.lineOne 		= :lineOne");
			hql.append(", A.lineTwo 	= :lineTwo");
			hql.append(", A.landmark 	= :landmark");
			hql.append(", A.stateCode 			= :state");
			hql.append(", A.countryCode 		= :country");
			hql.append(", A.postalCode 		= :postalCode");
			hql.append(", A.longitude 	= :longitude");
			hql.append(", A.latitude  	= :latitude");
			System.out.println("Country : "+address.getCityCode()+"   State   :"+address.getStateCode()+"   PostalCode : "+address.getPostalCode());
			/*	hql.append(", A.cityCode 		= :city");
			hql.append(", A.areaCode 	= :areaCode");
			*/
			hql.append(" WHERE A.addressId = :addressId");		
			
			
			Query query = session.createQuery(hql.toString());
			query.setParameter("lineOne", address.getLineOne());
			query.setParameter("lineTwo", address.getLineTwo());
			query.setParameter("landmark", address.getLandmark());
			query.setParameter("state", address.getStateCode());
			query.setParameter("country", address.getCountryCode());
			query.setParameter("postalCode", address.getPostalCode());
			query.setParameter("longitude", Double.valueOf(address.getLongitude().doubleValue()));
			query.setParameter("latitude", Double.valueOf(address.getLatitude().doubleValue()));
			
			/*	query.setParameter("city", Integer.valueOf(address.getCityCode()));
						query.setParameter("areaCode", address.getAreaCode().intValue());
			*/
			
			query.setParameter("addressId", address.getAddressId());			
			
			int status = query.executeUpdate();


			//session.saveOrUpdate(addr);
			//session.flush();
			logger.info("AddressDaoImpl -- Successfully updated Address -- Address Id : "+address.getAddressId());
			
		}catch(Exception ex){
			System.out.println("------------Exception --updateAddress-- AddressDaoImpl : "+ex);
		}
		return address;
	}

    public Address getAddress(Integer businessId){
    	Session session = null;
    	Address address = null;
    	try{
    		String hql = "From Address A WHERE A.businessId = :bussId";
    		session = sessionFactory.openSession();
    		Query query = session.createQuery(hql);
    		query.setParameter("bussId",businessId);
    		
    		List<Address> addrList = query.list();
    		
    		for(Address addr : addrList){
    			address = addr;
    		}
    	}catch(Exception ex){
    		System.out.println("------------Exception ---AddressDaoImpl -- : "+ex);
    	}
    	finally{
    		session.close();
    	}
    		return address;
    }
    
    public List<Address> getAddressListByCityStateAndCity(String contryCode,String stateCode,String cityCode,String areaCode){
    	Session session = null;
    	Address address = null;
    	List<Address> addressList =null;
    	try{
    		String hql = "FROM Address A WHERE A.countryCode = :ctryCode AND A.stateCode = :stateCode AND A.cityCode = :cityCode";
    		System.out.println("BusinessDaoImpl ---getBusinessList -- AreaCode : "+areaCode);
			
    		if(null != areaCode)
    			hql = hql + " AND A.areaCode = :areaCode";
    		System.out.println("BusinessDaoImpl ---getBusinessList -- HQL : "+hql);
			
    		session = sessionFactory.openSession();
    		Query query = session.createQuery(hql);
    		System.out.println(" AddressDaoImpl --getAddressListByCityStateAndCity -- After create Query ");
    		query.setParameter("ctryCode",contryCode);
    		query.setParameter("stateCode",stateCode);
    		query.setParameter("cityCode",Integer.valueOf(cityCode));
    		if(null != areaCode)
    		query.setParameter("areaCode",Integer.valueOf(areaCode));
    		System.out.println(" AddressDaoImpl --getAddressListByCityStateAndCity -- After Parameters Set");

    		addressList = query.list();
    		System.out.println(" AddressDaoImpl --getAddressListByCityStateAndCity -- Query List");

    	}catch(Exception ex){
    		System.out.println("------------Exception -- AddressDaoImpl --getAddressListByCityStateAndCity : "+ex);
    	}
    	finally{
    		session.close();
    	}
    		return addressList;
    	
    }
}
