package com.hayavadana.rating.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;

import com.hayavadana.rating.model.User;
import com.hayavadana.rating.dao.UserDao;

//@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean isUserExist(User user){
		boolean userExist = false;
		
		List<User> userList = null;
		System.out.println("--------UserDaoImpl-----Before Validation ");
		System.out.println("--------UserDaoImpl-----Login Id : "+ user.getLoginId());
		System.out.println("--------UserDaoImpl-----Password : "+ user.getPassword());
		userList = sessionFactory.openSession().createCriteria(User.class).list();
		
		for(User userObj : userList){
			if(userObj.getLoginId().equals(user.getLoginId()) &&
					userObj.getPassword().equals(user.getPassword())){
					userExist = true;
			}	
		}
		System.out.println("--------UserDaoImpl-----isUserExist : "+userExist);

		return userExist;
	}
}
