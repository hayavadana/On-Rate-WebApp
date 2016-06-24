package com.hayavadana.rating.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hayavadana.rating.bean.User;
import com.hayavadana.rating.bean.Users;

@Controller
@RequestMapping("/users")
public class DemoController {

	@RequestMapping(method=RequestMethod.GET,headers="Accept=*/*")
	public @ResponseBody Users getAllUsers(){
		User user1 = new User();
		user1.setFirstname("Hari");
		user1.setLastName("Prasad");
		
		User user2 = new User();
		user2.setFirstname("Jayanthi");
		user2.setLastName("Uppula");
		
		Users users = new Users();
		users.setUsers(new ArrayList<User>());
		users.getUsers().add(user1);
		users.getUsers().add(user2);
		 return users;
	}
}
