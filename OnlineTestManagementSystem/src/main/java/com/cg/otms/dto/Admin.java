package com.cg.otms.dto;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                              //Indicates that the class is an entity
@Table(name="AdminDetails")          //specifies the table name
public class Admin {
     
	@Id                               //It indicates primary key of an entity class
private String adminId;
   private String adminName;
	private String adminPassword;
	private LocalDate dateOfBirth;
	private String adminContact;
	
	public Admin()     //Default constructor
	{
		
	}
	//parameterized constructor
	public Admin(String adminId, String adminName, String adminPassword, LocalDate dateOfBirth, String adminContact) {
		
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.dateOfBirth = dateOfBirth;
		this.adminContact = adminContact;
	}
	//Getters and setters methods implementation
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdminContact() {
		return adminContact;
	}
	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	
}