package com.cg.otms.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import com.cg.otms.exception.IdNotFoundException;
import com.cg.otms.service.TestService;

@RestController                             //Indicates that the annotated class is controller
@RequestMapping("/test")                    //mapping HTTP requests onto methods
@CrossOrigin("http://localhost:4200")       //permitting cross-origin requests
public class TestController {

	@Autowired                  //enables to inject the object dependency implicitly
	
	TestService testservice;    //Enabling Dependency injection
	
	   //Adding Test details into database
		@PostMapping("/addTest")            //Mapping the URL to add test
		public ResponseEntity<String> addTest(@RequestBody Test test) {
			
			Test testDetails = testservice.addTest(test);    //Invoking a method - addTest
			//Condition - Checking whether the obtained object is null
			if (testDetails == null) {

				throw new IdNotFoundException("Test not added"); //if object is null throwing a IdNotFoundException

			} else {
				//returning the ResponseEntity<String> with httpStatus and headers
				return new ResponseEntity<String>("Test added successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Retrieving test details with particular testId
		@GetMapping("/getTest/{testId}")            //mapping the url to get details of particular test
		public ResponseEntity<Optional<Test>> getTestById(@PathVariable("testId") BigInteger testId) {
			
			
			Optional<Test> testDetails = testservice.getTestById(testId); //Invoking a method - getTestById
			//Condition - Checking whether the obtained object is null
			if (!testDetails.isPresent()) {
				throw new IdNotFoundException("Id does not exist,so we couldn't fetch details"); //if object is null throwing a IdNotFoundException
			} else {
				//returning the testDetails with httpStatus and headers
				return new ResponseEntity<Optional<Test>>(testDetails, new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Assigning test to a particular user
		@GetMapping("/assignTest/{testId}/{userId}")       //mapping the url to assign test to a particular user
		public ResponseEntity<String> assignTest(@PathVariable("testId") BigInteger testId,@PathVariable("userId") String userId) {
			
			User userDetails = testservice.assignTest(testId,userId);  //Invoking a method- assignTest
			//Condition - Checking whether the obtained object is null
			if (userDetails == null) {
				throw new IdNotFoundException("Id does not exist,so we couldn't assign Test"); //if object is null throwing a IdNotFoundException
	          		
			} else {
				//returning the ResponseEntity<String> with httpStatus and headers
				return new ResponseEntity<String>("Test assigned successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Retrieving question details with particular questionId
		@GetMapping("/getquestions/{testId}")            //mapping the url to get question details of a particular test 
		public ResponseEntity<Set<Question>> getQuestionById(@PathVariable("testId") BigInteger testId) {
			
			Set<Question> questionDetails = testservice.getQuestionById(testId); //Invoking a method- getQuestionById
			//Condition - Checking whether the obtained object is empty
			if (questionDetails.isEmpty()) {
				throw new IdNotFoundException("questions are not assigned");  //if object is empty throwing a IdNotFoundException
			} else {
				//returning the questionDetails with httpStatus and headers
				return new ResponseEntity<Set<Question>>(questionDetails, new HttpHeaders(), HttpStatus.OK);
			}
			
		}
		
		//Retrieving all the test details from the database
		@GetMapping("/testdetails")              //mapping the url to get all test details 
		public ResponseEntity<List<Test>> testDetails(){ 
			
			List<Test> testDetails=testservice.testDetails(); ////Invoking a method- testDetails
			//Condition - Checking whether the obtained object is empty
			if (testDetails.isEmpty()) {
				throw new IdNotFoundException("No Tests available");  //if object is empty throwing a IdNotFoundException
			} else {
				//returning the testDetails with httpStatus and headers
				return new ResponseEntity<List<Test>>(testDetails, new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Deleting Test with particular testId
		@DeleteMapping("/deleteTest/{testId}")   //mapping the url to delete a particular test
		public ResponseEntity<String> deleteTest(@PathVariable("testId") BigInteger testId) {
			
			String message = testservice.deleteTest(testId);  //Invoking a method - deleteTest
			//Condition - Checking whether the obtained object is null
			if (message == null) {
				throw new IdNotFoundException("Delete operation is unsuccessful");   //if object is empty throwing a IdNotFoundException
				
			
			} else {
				//returning the ResponseEntity<String> with httpStatus and headers
				return new ResponseEntity<String>("Delete operation is successful", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Exception Handling
		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<String> userNotFound(IdNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}
