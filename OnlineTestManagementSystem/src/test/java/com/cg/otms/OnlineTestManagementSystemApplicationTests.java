package com.cg.otms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.otms.service.TestService;

@SpringBootTest
class OnlineTestManagementSystemApplicationTests {
	@Autowired
	TestService testservice;
	@Test
	public void testdeletetest_positive() throws Exception
	
	{
		//BigInteger Id=111;
		//String s =testservice.deleteTest(Id);
	}

}
