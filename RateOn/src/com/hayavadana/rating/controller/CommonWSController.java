package com.hayavadana.rating.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hayavadana.rating.bean.CountryBean;

@Controller
public class CommonWSController {

	@ResponseBody
	@RequestMapping(value = "/getCountries",
    method = RequestMethod.GET,
    headers="Accept=application/json,application/xml")

	public CountryBean getCountries(HttpServletRequest request,HttpServletResponse response){
		CountryBean ctryBean = new CountryBean();
		System.out.println("CommonWSController--------getCountries");
		ctryBean.setCountryCode("IND");
		ctryBean.setCountryDesc("India");
		
		return ctryBean;
	}
}
