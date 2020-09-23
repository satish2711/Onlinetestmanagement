package com.cg.otms.controller;

import java.math.BigInteger;

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
import com.cg.otms.exception.IdNotFoundException;
import com.cg.otms.service.QuestionService;


@RestController       					//Indicates that the annotated class is controller
@RequestMapping("/testquestions")		//mapping web requests onto methods

@CrossOrigin()	//permitting cross-origin requests
public class QuestionController {
	
@Autowired								//enables to inject the object dependency implicitly
QuestionService questionservice;		 //Enabling Dependency injection

public void setQuestionservice(QuestionService questionservice)
{
	this.questionservice = questionservice;
}

public QuestionController()
{
	
}
//Adding question to test with particular testId
	@PostMapping("/addQuestion/{testId}")		//Mapping the URL to add the question
	public ResponseEntity<String> addQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Test testDetails = questionservice.addQuestion(testId,question);//Invoking a method - addQuestion
		//Condition - Checking whether the obtained object is null
		if (testDetails == null) {

			throw new IdNotFoundException("Question not added");	//if object is null throwing a IdNotFoundException

		} else {
			//returning the ResponseEntity<String> with httpStatus and headers
			return new ResponseEntity<String>("Question added successfully", new HttpHeaders(), HttpStatus.OK);
		}
		
	}
	
	
	 @DeleteMapping("/deleteQuestion/{questionId}")	//Mapping the URL to delete the question
     public ResponseEntity<String> deleteQuestion(@PathVariable BigInteger questionId)
     {
  	   try
  	   {
  		   questionservice.deleteQuestion(questionId);
  		   return new ResponseEntity<String>("Question Details Deleted Successfully",HttpStatus.OK);
  	   }
  	   catch(Exception ex)
  	 	  {
  	 		 return new ResponseEntity<String>("Deletion Failed",HttpStatus.BAD_REQUEST);
  	 	  }
     }
	 
	//Calculating total marks in the test
		@GetMapping("/calculateTotalMarks/{testId}")	//Mapping the URL to Calculate the marks
		public ResponseEntity<Test> calculateTotalMarks(@PathVariable BigInteger testId ) {
			Test testDetails = questionservice.calculateTotalMarks(testId);
			if (testDetails == null) {

				throw new IdNotFoundException("Test details not found");		//if object is null throwing a IdNotFoundException
			}
			else
			{
				System.out.println("dfhgjhklhgjvhgvmbkjg");
			return new ResponseEntity<Test>(testDetails,HttpStatus.OK);
			}
		}
	

	//Exception Handling
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}