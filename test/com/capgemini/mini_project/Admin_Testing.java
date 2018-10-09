package com.capgemini.mini_project;

import static org.junit.Assert.*;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.University_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class Admin_Testing {
	
	static Connection conn;
	static University_dao_impl admin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn=null;
		admin=new University_dao_impl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn=null;
	}

	@Test
	public void test_login() throws UniversityException {
		Users user=new Users();
		user.setLoginId("adda");
		user.setPassword("laxman");
		Assert.assertEquals(true,admin.check_login_dao(user));
	}

}
