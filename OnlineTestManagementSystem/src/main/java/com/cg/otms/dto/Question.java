package com.cg.otms.dto;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity                                   //Indicates that the class is an entity
@Table(name="QuestionDetails")            //specifies the table name
public class Question  {

	@Id                                     //It indicates primary key of an entity class
	private BigInteger questionId;
   
	@ManyToOne(fetch = FetchType.LAZY)      //It indicates many to one relation 
	@JoinColumn(nullable=false)        
	private Test test;
	
	private String questionOptions;
	
	private String questionTitle;
	private int questionAnswer;
	private int questionMarks;

	private int choosenAnswer; 
	private int marksScored;
	
	//Getters and setters methods implementation
	public BigInteger getQuestionId() {
		return questionId;
	}
	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}
	
	public void setTest(Test test) {
		this.test = test;
	}
	public String getQuestionOptions() {
		return questionOptions;
	}
	public void setQuestionOptions(String questionOptions) {
		this.questionOptions = questionOptions;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public int getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(int questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public int getQuestionMarks() {
		return questionMarks;
	}
	public void setQuestionMarks(int questionMarks) {
		this.questionMarks = questionMarks;
	}
	public int getChoosenAnswer() {
		return choosenAnswer;
	}
	public void setChoosenAnswer(int chosenAnswer) {
		this.choosenAnswer = chosenAnswer;
	}
	public int getMarksScored() {
		return marksScored;
	}
	public void setMarksScored(int marksScored) {
		this.marksScored = marksScored;
	}
	
}