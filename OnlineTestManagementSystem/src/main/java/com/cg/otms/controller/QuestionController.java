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


@RestController
@RequestMapping("/testquestions")

@CrossOrigin("http://localhost:4200")
public class QuestionController {
	
@Autowired
QuestionService questionservice;

//Adding question to test with particular testId
	@PostMapping("/addQuestion/{testId}")
	public ResponseEntity<String> addQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Test testDetails = questionservice.addQuestion(testId,question);
		if (testDetails == null) {

			throw new IdNotFoundException("Question not added");

		} else {
			return new ResponseEntity<String>("Question added successfully", new HttpHeaders(), HttpStatus.OK);
		}
		
	}
	
	
	 @DeleteMapping("/deleteQuestion/{questionId}")
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
		@GetMapping("/calculateTotalMarks/{testId}")
		public Test calculateTotalMarks(@PathVariable BigInteger testId ) {
			Test testDetails = questionservice.calculateTotalMarks(testId);
			if (testDetails == null) {

				throw new IdNotFoundException("Test details not found");
			}
			else
			{
			return testDetails;
			}
		}
	

	//Exception Handling
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}