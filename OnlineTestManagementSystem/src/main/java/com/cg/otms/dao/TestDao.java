package com.cg.otms.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.otms.dto.Test;

@Repository                //Indicates that an annotated class is a Repository
public interface TestDao extends JpaRepository<Test,BigInteger>{
	
}