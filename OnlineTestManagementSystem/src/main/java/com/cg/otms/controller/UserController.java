package com.cg.otms.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import com.cg.otms.exception.UserDefinedException;
import com.cg.otms.service.UserService;

@RestController                         
@RequestMapping("/users")               
@CrossOrigin("http://localhost:4200") 
public class UserController {
@Autowired                
UserService userservice; 
	/**
	 * This method used for userLogin 

	 * @return String valid user if user details are present
	 */
	/*@GetMapping("/UserLogin/{userId}/{password}")  
	public ResponseEntity<String> userLogin(@PathVariable("userId") String userId,@PathVariable("password") String password) {
		
		Optional<User> userDetails = userservice.userLogin(userId,password); //Invoking a method - userLogin
		//Condition - Checking whether the obtained object is null
		if(!userDetails.isPresent())
		{
			throw new UserDefinedException("User credentials are incorrect"); 
		}
		else
		{
			return new ResponseEntity<String>("valid user", new HttpHeaders(), HttpStatus.OK);     //returning string -valid user
		}
	}
	
	
	/**
	 * This method used for retrieving userTest details
	 * @return testDetails
	 */
/*	@GetMapping("/UserTest/{userId}")                   
	public ResponseEntity<Test> userTest(@PathVariable("userId") String userId) {
		Test testDetails = userservice.userTest(userId);         //Invoking a method - userTest
		//Condition - Checking whether the obtained object is null
   if (testDetails==null) 
   {
		throw new UserDefinedException("Test is not assigned to the User"); 
		}
   else
   {
	   return new ResponseEntity<Test>(testDetails, new HttpHeaders(), HttpStatus.OK); //returning the object -testDetails
   }
	}*/
	//user login method
		@GetMapping("/UserLogin/{userId},{password}")
		public String userLogin(@PathVariable("userId") String userId,@PathVariable("password") String password) {
			Optional<User> userDetails = userservice.userLogin(userId,password);
			
			return userDetails.toString();
		}
		
		//Retrieving test details of particular User
		@GetMapping("/UserTest/{userId}")
		public Test userTest(@PathVariable("userId") String userId) {
			Test testDetails = userservice.userTest(userId);
	     if (testDetails==null) {
				
				throw new  UserDefinedException("Test is not assigned to the User");
			}
	     else
	     {
			return testDetails;
	     }
		}
		
	
	/**
	 * This method used for user registration
	 * @return String User created successfully
	 */
	@PostMapping("/UserRegistration")
	public ResponseEntity<String> userRegistration(@RequestBody User user) {
		User userDetails = userservice.userRegistration(user);
		//Condition - Checking whether the obtained object is null
		if (userDetails == null) {
			return new ResponseEntity<String>("userId already Exist", new HttpHeaders(), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("User created successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}			
	
	/**
	 * This method used for password recovery
	 * @return String Password updated successfully
	 */
		@GetMapping("/PasswordRecovery/{userId},{password},{rePassword}")
	public ResponseEntity<String> passwordRecovery(@PathVariable("userId") String userId,@PathVariable("password") String password,@PathVariable("rePassword") String rePassword) {
		int status = userservice.passwordRecovery(userId,password,rePassword);
		//Condition - Checking whether the obtained object is 0
		if (status == 0) {
				
			throw new UserDefinedException("Update Operation Unsuccessful,Provided UserId does not exist");
			
			}
		else {
				return new ResponseEntity<String>("Password updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		
   @ExceptionHandler(UserDefinedException.class)
	public ResponseEntity<String> userNotFound(UserDefinedException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	
	}
}