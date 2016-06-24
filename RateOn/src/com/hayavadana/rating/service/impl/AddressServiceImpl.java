package com.hayavadana.rating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.dao.AddressDao;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Business;
import com.hayavadana.rating.service.AddressService;
import com.hayavadana.rating.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public Address saveAddress(Address address){
		
		//address.setAddressId(business.getBusinessId());
	 	address  = addressDao.saveAddress(address);
		
		return address;
	}

	public AddressBean getAddress(Integer businessId){
		Address address = null;
		AddressBean addrBean = null;
		
		address = addressDao.getAddress(businessId);
		 if(null != address)
			 addrBean = commonUtil.getAddressBean(address);
		
		return addrBean;
	}
}
