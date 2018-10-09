package com.capgemini.mini_project;


import java.sql.Connection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.MacDAO;
import com.capgemini.university.exception.UniversityException;

public class MACTesting {
	
	static Connection conn;
	static MacDAO mac;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn=null;
		mac=new MacDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn=null;
	}

	@Test
	public void testLogin() throws UniversityException {
		Users user=new Users();
		user.setLoginId("krish");
		user.setPassword("srikar");
		Assert.assertEquals(true,mac.checkLoginDao(user));
	}
	
	@Test
	public void testUpdateInterviewDate() throws UniversityException {
		Assert.assertEquals(1,mac.updateInterviewDateDao(7583,"12/10/2018"));
	}
	
	@Test
	public void testUpdateConfirmStatus() throws UniversityException {
		Assert.assertEquals(1,mac.updateConfirmStatus(7583));
	}
	
    public void testUpdateRejectStatus() throws UniversityException{
    	Assert.assertEquals(1,mac.updateRejectStatus(7583));
    }
    
    @Test
	public void testdisplaySchedulesDao() throws UniversityException {
		Assert.assertNotNull(mac.displaySchedulesDao());
	}
    
    @Test
   	public void testdisplayApplicationDao() throws UniversityException {
   		Assert.assertNotNull(mac.displayApplicationDao("maths"));
   	}
    
    @Test
   	public void testdisplayNewApplications() throws UniversityException {
   		Assert.assertNotNull(mac.displayNewApplications());
   	}
    
    @Test
   	public void testdisplayApprovedApplications() throws UniversityException {
   		Assert.assertNotNull(mac.displayApprovedApplications());
   	}

}
