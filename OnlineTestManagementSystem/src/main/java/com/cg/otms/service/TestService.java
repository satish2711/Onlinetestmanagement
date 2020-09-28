package com.cg.otms.service;

import org.springframework.stereotype.Service;

import com.cg.otms.dao.TestDao;
import com.cg.otms.dao.UserDao;
import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service                   //Indicates that the annotated class is service
@Transactional             //Defines the scope of a single database transaction
public class TestService {
	
	@Autowired              //enables to inject the object dependency implicitly
	TestDao testdao;        //Enabling Dependency injection
	@Autowired              //enables to inject the object dependency implicitly
	UserDao userdao;        //Enabling Dependency injection
		
	//Adding Test 
	public Test addTest(Test test)
	{
		return testdao.save(test);   //saves the given entity
	}
	
   //Retrieving all Test details from database
	public List<Test> testDetails() {
		return testdao.findAll();           //returns all the instances of the type
	}

	//Update Test
	public Test updateTest(BigInteger testId, Test test)
	{
		if(testdao.existsById(testId))
		{
			Test t=testdao.getOne(testId);
			test.setTestQuestions(t.getTestQuestions());
			 return testdao.save(test);
		}
		else
		{
			return null;
		}
	}
	
	
	//Delete Test
	public String deleteTest(BigInteger testId)
	{
		
		if(testdao.existsById(testId))       //Returns whether an entity with the given id exists. 
		{
		  testdao.deleteById(testId);          //Deletes the entity with the given id.
		  return "deleted successfully";
		}
		else
		{
			return null;
		}
	}

	//Retrieving Test details with particular testId
	 public Optional<Test> getTestById(BigInteger testId) {
			
			return testdao.findById(testId);        //Retrieves an entity by its id.
		}

	 //Retrieving Question details with particular testId
	public Set<Question> getQuestionById(BigInteger testId) {
		Test t=testdao.getOne(testId);           //Returns a reference to the entity with the given identifier
		return t.getTestQuestions();
	}

	
	//Assigning Test to particular User
	public User assignTest(BigInteger testId, String userId) {
		//Condition : To check whether test and user exists by id
		if(testdao.existsById(testId)&&userdao.existsById(userId))
		{
			Test t=testdao.getOne(testId);  //Invoking a method
			User u=userdao.getOne(userId);
			t.setUser(u);
			u.setUserTest(t);
			return userdao.save(u);    //Saves a given entity
			
			
		}
		else
		{
		return null;
	}
	}
		
	
	


}
