package com.capgemini.mini_project;

import static org.junit.Assert.*;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.MAC_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class MAC_Testing {
	
	static Connection conn;
	static MAC_dao_impl mac;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn=null;
		mac=new MAC_dao_impl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn=null;
	}

	@Test
	public void test_login() throws UniversityException {
		Users user=new Users();
		user.setLoginId("srikar");
		user.setPassword("cr");
		Assert.assertEquals(true,mac.check_login(user));
	}
	
	@Test
	public void test_update_interview_date() throws UniversityException {
		Assert.assertEquals(1,mac.update_interview_date_dao(7583,"12/10/2018"));
	}
	
	@Test
	public void test_update_status() throws UniversityException {
		Assert.assertEquals(1,mac.update_status_dao(7583));
	}
	
	/*
	
	@Test
	public void test_display_applications() throws UniversityException {
		Application app=new Application();
		app.setApplicantId(7583);
		app.setFullName("lakshman");
		app.setDateOfBirth("26/07/1995");
	}
	
	*/

}
