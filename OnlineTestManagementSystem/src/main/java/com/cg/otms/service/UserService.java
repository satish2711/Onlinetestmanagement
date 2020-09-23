package com.cg.otms.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.otms.dao.UserDao;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
@Service                     //Indicates that the annotated class is service
@Transactional               //Defines the scope of a single database transaction
public class UserService {
	@Autowired               //enables to inject the object dependency implicitly
	private UserDao userdao; //Enabling Dependency injection
	
	//User Login method
	public Optional<User> userLogin(String userId,String password)
	{
		
		return userdao.userLogin(userId, password); //Invoking a method - userLogin
		
	}

    //Retrieving test details of particular User
	public Test userTest(String userId) {
		User u=userdao.getOne(userId);        //Returns a reference to the entity with the given identifier
		return u.getUserTest();
	}
	
}

