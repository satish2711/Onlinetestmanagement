package com.cg.otms.dto;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity                               //Indicates that the class is an entity
@Table(name="Userdetails")            //specifies the table name
public class User {
	@Id                               //It indicates primary key of an entity class
	@Column(length=10)                // specifying the column length
private String userId;
	//Implementing onetoOne relation and cascade=CascadeType.ALL is used to prevent flushing
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true) //It indicates one to one relation
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})//
	@JoinColumn(name = "userTest")   //Joining the column
	private Test userTest;
	@Column(length=15)                 // specifying the column length
private String password;
	@Column(length=15)
private String rePassword;
	@Column(length=15)                   // specifying the column length
private BigInteger phonenumber;
	@Column(length=25)
	private String emailId;
	//Getters and setters methods implementation
public String getUserId() {
	return userId;
}
public Test getUserTest() {
	return userTest;
}
public void setUserTest(Test userTest) {
	this.userTest = userTest;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRePassword() {
	return rePassword;
}
public void setRePassword(String rePassword) {
	this.rePassword = rePassword;
}
public BigInteger getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(BigInteger phonenumber) {
	this.phonenumber = phonenumber;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}

}