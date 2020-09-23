package com.cg.otms.dto;

import java.math.BigInteger;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity                             //Indicates that the class is an entity
@Table(name="TestDetails")          //specifies the table name
public class Test {

	@Id                              //It indicates primary key of an entity class
	private BigInteger id;
	private String title;
	private int testTotalMarks=0;
	private int testMarksScored=0;
	private LocalTime duration;
	private LocalDateTime starttime;
	private LocalDateTime endtime;
	//Implementing onetoMany relation and cascade=CascadeType.ALL is used to prevent flushing
	@OneToMany(mappedBy="test",cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})fetch = FetchType.LAZY//
	private Set<Question> testQuestions;
    //Implementing onetoOne relation
	@OneToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "userId")          //Joining a column                        
	private User user;
	//Getters and setters methods implementation
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTestTotalMarks() {
		return testTotalMarks;
	}

	public void setTestTotalMarks(int testTotalMarks) {
		this.testTotalMarks = testTotalMarks;
	}

	public int getTestMarksScored() {
		return testMarksScored;
	}

	public void setTestMarksScored(int testMarksScored) {
		this.testMarksScored = testMarksScored;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	public LocalDateTime getStarttime() {
		return starttime;
	}

	public void setStarttime(LocalDateTime starttime) {
		this.starttime = starttime;
	}

	public LocalDateTime getEndtime() {
		return endtime;
	}

	public void setEndtime(LocalDateTime endtime) {
		this.endtime = endtime;
	}

	public Set<Question> getTestQuestions() {
		return testQuestions;
	}

	public void setTestQuestions(Set<Question> testQuestions) {
		this.testQuestions = testQuestions;
	}
	
}