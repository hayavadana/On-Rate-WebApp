package com.hayavadana.rating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hayavadana.rating.model.User;
import com.hayavadana.rating.dao.UserDao;
import com.hayavadana.rating.service.UserService;
import com.hayavadana.rating.util.MailUtil;

//@Service("userService")
//@Transactional(propagation = Propagation.SUPPORTS,readOnly=true)
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MailUtil mailUtil;
	
	public boolean checkIfUserExist(User user){
		System.out.println("--------UserServiceImpl-----checkIfUserExist-----");
		return userDao.isUserExist(user);
	}
}
