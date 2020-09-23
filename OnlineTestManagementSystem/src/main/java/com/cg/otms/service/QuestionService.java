package com.cg.otms.service;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.otms.dao.QuestionDao;
import com.cg.otms.dao.TestDao;
import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;

@Service								//Indicates that the annotated class is service
@Transactional							//Defines the scope of a single database transaction
public class QuestionService {
@Autowired								//enables to inject the object dependency implicitly
TestDao testdao;                 //Enabling Dependency injection
public void setTestDao(TestDao testDao) 
{
	this.testdao = testDao;
}
@Autowired								//enables to inject the object dependency implicitly
QuestionDao questiondao;				//Enabling Dependency injection

public void setQuestiondao(QuestionDao questionDao)
{
	this.questiondao = questionDao;
}

public QuestionService() {}

//Add question
	public Test addQuestion(BigInteger testId,Question question)
	{
	if(testdao.existsById(testId))			//Checking for the existence of entity
	{
		Test t=testdao.getOne(testId);		 //Returns a reference to the entity with the given identifier
		question.setTest(t);
		t.getTestQuestions().add(question);
		t.setTestTotalMarks(t.getTestTotalMarks()+question.getQuestionMarks());
		
		return testdao.save(t);			//saves the given entity
	}
	else
	{
    return null;
	}
	}
	
	//Delete question
	 public String deleteQuestion(BigInteger questionId)
	    {
	    	questiondao.deleteById(questionId);		 //Retrieves an entity by its id.
	    	return "Question Details Deleted";
	    }
	 
	 
	 //calculate total marks
	 public Test calculateTotalMarks(BigInteger testId) {
		
		 Test t=testdao.getOne(testId);
	     Set<Question> s=t.getTestQuestions();
	     int testTotalMarks=0;
	     int testMarksScored=0;
	     Iterator<Question> it = s.iterator(); 
	     while (it.hasNext()) 
	     {
	          Question q= it.next(); 
	          System.out.println("hiiiiiiiiiiiiiiiiiiiiiiii");
	          if(q.getChoosenAnswer()==q.getQuestionAnswer())
	          {
	        	  q.setMarksScored(q.getQuestionMarks());
	          }
	          testTotalMarks=testTotalMarks+q.getQuestionMarks();
	          testMarksScored=testMarksScored+q.getMarksScored();
	     }
		t.setTestTotalMarks(testTotalMarks);
		t.setTestMarksScored(testMarksScored);
		System.out.println("asdfghjklkjhgfd"); 
		return t;
	}


}
