package com.capgemini.mini_project;


import java.sql.Connection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.university.bean.Application;
import com.capgemini.university.dao.StudentDAO;
import com.capgemini.university.exception.UniversityException;

public class StudentTesting {
	
	static Connection conn;
	static StudentDAO student;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn=null;
		student=new StudentDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn=null;
		student=null;
	}

	@Test
	public void test_display_status() throws UniversityException {
		Assert.assertEquals("Confirmed",student.displayStatusDao(7583));
	}
	

	@Test
	public void test_insert_application() throws UniversityException {
		Application app=new Application();
		app.setFullName("Srikar Krishna");
		app.setDateOfBirth("25/06/1997");
		app.setEmailId("srikar.ka@gmail.com");
		app.setHighestQualification("b.tech");
		app.setGoals("doctor");
		app.setMarksObtained(80);
		app.setScheduledProgramId("s_1");
		Assert.assertFalse(student.insertApplicationDao(app)<0);
	}

}
