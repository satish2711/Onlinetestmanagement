package com.cg.otms.service;



import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.otms.dao.UserDao;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
@Service                     
@Transactional               
public class UserService {
	@Autowired               
	private UserDao userdao; 
	
	/**
	 * Adding user details to database
	 */
	
	public User userRegistration(User user)
		{
		 if(!userdao.existsById(user.getUserId()))
			{
				return userdao.save(user);
				
			}
		 else
			{
			return null;
			}
		}
	/**
	 * User login
	 */
	public Optional<User> userLogin(String userId,String password)
	{
		
		return userdao.userLogin(userId, password); //Invoking a method - userLogin
		
	}

	/**
	 * Retrieving userTest details by userId
	 */
	public Test userTest(String userId) {
		User u=userdao.getOne(userId);        //Returns a reference to the entity with the given identifier
		return u.getUserTest();
	}
	/**
	 * Updating password in database
	 */
	public int passwordRecovery(String userId,String password,String rePassword)
	{
		if(userdao.existsById(userId))
		{
		return userdao.update(userId, password, rePassword);	
		}
		else
		{
			return 0;
		}			
			}
}