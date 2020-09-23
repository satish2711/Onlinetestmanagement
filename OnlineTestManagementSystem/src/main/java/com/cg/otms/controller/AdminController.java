package com.cg.otms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Admin;
import com.cg.otms.service.AdminService;

@RestController                       //Indicates that the annotated class is controller
@RequestMapping("/admin")             //mapping web requests onto methods
@CrossOrigin("http://localhost:4200") //permitting cross-origin requests
public class AdminController {
@Autowired                      //enables to inject the object dependency implicitly
AdminService adminservice;      //Enabling Dependency injection
    

//Administrator login method
	@GetMapping("/adminLogin/{adminId},{adminPassword}")      //Mapping the URL
	public String adminLogin(@PathVariable("adminId") String adminId,@PathVariable("adminPassword") String adminPassword) {
		
		Optional<Admin> adminDetails = adminservice.adminLogin(adminId,adminPassword); //Invoking a method - adminLogin
		//Condition - Checking whether the obtained object is null
		if(adminDetails.isPresent())
		{
			return "valid";
		}
		else
		{
		return "invalid";
		}
	}
	
	//Inserting Administrator details into database
	@PostMapping("/addAdmin")                 //Mapping the URL to add administrator details
	public void addAdmin()
	{
		adminservice.addAdmin();               //Invoking a method - addAdmin
	}
}
