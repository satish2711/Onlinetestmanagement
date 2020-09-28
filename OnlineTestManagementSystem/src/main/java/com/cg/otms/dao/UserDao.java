package com.cg.otms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.otms.dto.User;


@Repository            //Indicates that an annotated class is a Repository
public interface UserDao extends JpaRepository<User,String>{
	
	@Query("select userId,emailId,password,phonenumber,rePassword from User where userId=?1 and password=?2")
	Optional<User> userLogin(String userId,String password);
	
	
	@Modifying
	@Query("UPDATE User u SET u.password = :password,u.rePassword = :rePassword WHERE u.userId = :userId")
	int update(@Param("userId") String userId,@Param("password") String password,@Param("rePassword") String rePassword);

}