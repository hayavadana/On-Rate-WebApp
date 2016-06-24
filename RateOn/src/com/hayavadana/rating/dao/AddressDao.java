package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Business;

public interface AddressDao {
	
    public Address saveAddress(Address address);
    public Address updateAddress(Address address);
    public Address getAddress(Integer businessId);  
    public List<Address> getAddressListByCityStateAndCity(String contryCode,String stateCode,String cityCode,String areaCode);
  }
