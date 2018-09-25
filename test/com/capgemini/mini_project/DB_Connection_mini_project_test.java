package com.capgemini.mini_project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.*;

import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.util.DBConnection;

public class DB_Connection_mini_project_test {
	
	static Connection conn;
	
	@BeforeClass
	public static void initalize(){
		conn=null;
	}
	
	@Before
	public void before_test(){
		System.out.println("This will print before every test case");
	}

	@Test
	public void testGetConnection() throws SQLException, IOException, UniversityException {
		conn=DBConnection.getInstance().getConnection();
		Assert.assertNotNull(conn);
	}
	
	@After
	public void after_test(){
		System.out.println("This will print after every test case");
	}
	
	@AfterClass
	public static void destroy(){
		conn=null;
	}
}
