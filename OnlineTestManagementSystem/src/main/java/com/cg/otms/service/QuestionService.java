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

@Service
@Transactional
public class QuestionService {
@Autowired
TestDao testdao;
@Autowired
QuestionDao questiondao;

//Add question
	public Test addQuestion(BigInteger testId,Question question)
	{
	if(testdao.existsById(testId))
	{
		Test t=testdao.getOne(testId);
		question.setTest(t);
		t.getTestQuestions().add(question);
		t.setTestTotalMarks(t.getTestTotalMarks()+question.getQuestionMarks());
		
		return testdao.save(t);
	}
	else
	{
    return null;
	}
	}
	
	//Delete question
	 public String deleteQuestion(BigInteger questionId)
	    {
	    	questiondao.deleteById(questionId);
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
	          
	          if(q.getChoosenAnswer()==q.getQuestionAnswer())
	          {
	        	  q.setMarksScored(q.getQuestionMarks());
	          }
	          testTotalMarks=testTotalMarks+q.getQuestionMarks();
	          testMarksScored=testMarksScored+q.getMarksScored();
	          q.setTest(t);
	          questiondao.save(q);
		       
	     }
		t.setTestTotalMarks(testTotalMarks);
		t.setTestMarksScored(testMarksScored);
	     
		return t;
	}


}
