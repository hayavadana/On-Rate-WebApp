package com.hayavadana.rating.service;

import com.hayavadana.rating.bean.AddressBean;
import com.hayavadana.rating.model.Address;

public interface AddressService {
	public Address saveAddress(Address address);
	public AddressBean getAddress(Integer businessId);
}
