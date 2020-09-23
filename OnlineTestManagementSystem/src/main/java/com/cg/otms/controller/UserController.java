package com.cg.otms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import com.cg.otms.exception.UserDefinedException;
import com.cg.otms.service.UserService;

@RestController                         //Indicates that the annotated class is controller
@RequestMapping("/users")               //mapping web requests onto methods
@CrossOrigin("http://localhost:4200")   //permitting cross-origin requests
public class UserController {
@Autowired                //enables to inject the object dependency implicitly
UserService userservice;  //Enabling Dependency injection
    //user login method
	@GetMapping("/UserLogin/{userId}/{password}")  //Mapping the url
	public String userLogin(@PathVariable("userId") String userId,@PathVariable("password") String password) {
		
		Optional<User> userDetails = userservice.userLogin(userId,password); //Invoking a method userLogin
		//Condition - Checking whether the obtained object is null
		if(userDetails.isPresent())
		{
			return "valid";
		}
		else
		{
		return "invalid";
		}
	}
	
	//Retrieving test details of particular User
	@GetMapping("/UserTest/{userId}")                   //Mapping the url to get user test details
	public Test userTest(@PathVariable("userId") String userId) {
		Test testDetails = userservice.userTest(userId);         //Invoking a method userTest
		//Condition - Checking whether the obtained object is null
   if (testDetails==null) {
			
			throw new UserDefinedException("Test is not assigned to the User"); //if the object is null throwing UserDefinedException
		}
   else
   {
		return testDetails;
   }
	}
	
   @ExceptionHandler(UserDefinedException.class)
	public ResponseEntity<String> userNotFound(UserDefinedException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	
	}
}
