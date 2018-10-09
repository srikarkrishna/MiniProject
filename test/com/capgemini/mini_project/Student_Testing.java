package com.capgemini.mini_project;

import static org.junit.Assert.*;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.university.bean.Application;
import com.capgemini.university.dao.student_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class Student_Testing {
	
	static Connection conn;
	static student_dao_impl student;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn=null;
		student=new student_dao_impl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn=null;
		student=null;
	}

	@Test
	public void test_display_status() throws UniversityException {
		Assert.assertEquals("Confirmed",student.display_status_dao(7583));
	}
	

	@Test
	public void test_insert_application() throws UniversityException {
		Application app=new Application();
		app.setFullName("Srikar Krishna");
		app.setDateOfBirth("25/06/1997");
		app.setEmailId("srikar.krishna@gmail.com");
		app.setHighestQualification("b.tech");
		app.setGoals("doctor");
		app.setMarksObtained(80);
		app.setScheduledProgramId("s_1");
		Assert.assertFalse(student.insert_application_dao(app)<0);
	}

}
