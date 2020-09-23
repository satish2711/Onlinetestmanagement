package com.cg.otms.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.otms.dao.AdminDao;
import com.cg.otms.dto.Admin;

@Service                   //Indicates that the annotated class is service
@Transactional             //Defines the scope of a single database transaction
public class AdminService {
	@Autowired             //enables to inject the object dependency implicitly
	AdminDao admindao;     //Enabling Dependency injection
	
	/*Admin login method will return admin details with particular adminId and adminPassword*/
	public Optional<Admin> adminLogin(String adminId,String adminPassword)
	{
		
		return admindao.adminLogin(adminId, adminPassword); //Invoking a method - adminLogin
	}
	

			
	/*Admin details are inserted into database manually*/
	public void addAdmin()
	{
		LocalDate ld=LocalDate.of(1978,04,30);    //Date without a time zone
		LocalDate ld1=LocalDate.of(1985,06,10);
		LocalDate ld2=LocalDate.of(1983,03,20);
		//list backed by a specified array
		List<Admin> admin=Arrays.asList(
				(new Admin("school@1","narayan","narayan@45",ld,"1234567891")),
				new Admin("school@2","lakshmi","lakshmi@25",ld1,"0321654987"),
				new Admin("school@3","sathish kumaran","kumaran@45",ld2,"7896541230")
				
				);
				
		admindao.saveAll(admin);  //enabling to save all entities
	}
}

