package com.hayavadana.rating.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hayavadana.rating.bean.Employee;

@Controller
@RequestMapping(value="/json")
public class JsonOutputController {

	@RequestMapping("/emp/{name}")
	public @ResponseBody Employee generateJsonResponse(@PathVariable("name")String name){
		Employee emp = new Employee(1001,"Hari");
		return emp;
	}
}
